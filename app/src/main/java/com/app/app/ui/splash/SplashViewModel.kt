package com.app.app.ui.splash

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.SplashRepo

class SplashViewModel(var splashRepo: SplashRepo) : BaseViewModel<SplashRepo>(splashRepo) {
    val splashTimeOut = MutableLiveData<Boolean>()

    init {
        startHandler()
    }

    // make splashTimeOut to be true after 3 seconds
    private fun startHandler() {
        Handler().postDelayed({ splashTimeOut.postValue(true) }, 3000)
    }
}