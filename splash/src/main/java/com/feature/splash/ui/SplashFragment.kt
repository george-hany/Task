package com.feature.splash.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.core.base.BaseFragment
import com.feature.splash.BR
import com.feature.splash.R
import com.feature.splash.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    val splashViewModel: SplashViewModel by viewModel()

    override fun bindingVariable(): Int {
        return BR.viewModel
    }

    override fun layoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun getViewModel(): SplashViewModel {
        return splashViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToLoginObservation()
    }

    private fun navigateToLoginObservation() {
        splashViewModel.navigateToLogin.observe(viewLifecycleOwner, Observer {
            if (it) {
            }
        })
    }
}
