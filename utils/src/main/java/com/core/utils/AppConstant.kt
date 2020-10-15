package com.core.utils

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

object AppConstant {
    const val deepLinkArgumentsKey = "android-support-nav:controller:deepLinkIntent"
    const val CALORIES = "CALORIES"
    const val FATS = "FATS"

    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun verifyStoragePermissions(activity: Activity): Boolean {
        // Check if we have write permission
        val permissionRead = ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val permissionWrite =
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return if (permissionRead != PackageManager.PERMISSION_GRANTED ||
            permissionWrite != PackageManager.PERMISSION_GRANTED
        ) { // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
            false
        } else {
            true
        }
    }
}
