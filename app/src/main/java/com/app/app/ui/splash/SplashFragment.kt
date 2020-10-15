package com.app.app.ui.splash

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.app.app.BR
import com.app.app.R
import com.app.app.databinding.FragmentSplashBinding
import com.core.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    val splashViewModel: SplashViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startRotation()
        splashTimeOutObserver()
    }

    // set rotate animation to loading image
    private fun startRotation() {
        val rotation: Animation = AnimationUtils.loadAnimation(context, R.anim.rotation)
        viewDataBinding.loading.startAnimation(rotation)
    }

    // observe on splashTimeOut to trigger if changes happened
    private fun splashTimeOutObserver() {
        splashViewModel.splashTimeOut.observe(viewLifecycleOwner, Observer {
            if (it) {
                // go to list screen
                navigation.navigate(R.id.recipesListFragment)
            }
        })
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_splash

    override fun getViewModel(): SplashViewModel = splashViewModel


}