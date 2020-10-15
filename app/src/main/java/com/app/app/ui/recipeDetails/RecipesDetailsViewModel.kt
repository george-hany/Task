package com.app.app.ui.recipeDetails

import androidx.lifecycle.MutableLiveData
import com.app.app.ui.recipesList.model.RecipesItemUIModel
import com.core.base.BaseViewModel
import com.core.data.repos.RecipesDetailsRepo

class RecipesDetailsViewModel(var recipesDetailsRepo: RecipesDetailsRepo) :
    BaseViewModel<RecipesDetailsRepo>(recipesDetailsRepo) {
    val recipesItemUIModel = MutableLiveData<RecipesItemUIModel>()
}