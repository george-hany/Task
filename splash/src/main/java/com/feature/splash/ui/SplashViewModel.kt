package com.feature.splash.ui

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.SplashRepo

class SplashViewModel(private val splashRepo: SplashRepo) : BaseViewModel<SplashRepo>(splashRepo) {

    val navigateToLogin = MutableLiveData<Boolean>()

    init {
        startHandler()
    }

    private fun startHandler() {
        Handler().postDelayed({
            navigateToLogin.postValue(true)
        }, 5000)
    }
}
