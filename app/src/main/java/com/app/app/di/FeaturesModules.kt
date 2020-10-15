package com.app.app.di

import com.app.app.ui.recipeDetails.RecipesDetailsViewModel
import com.app.app.ui.recipesList.RecipesListViewModel
import com.app.app.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var allFeatures = module {
    viewModel { SplashViewModel(get()) }
    viewModel { RecipesListViewModel(get()) }
    viewModel { RecipesDetailsViewModel(get()) }
}
