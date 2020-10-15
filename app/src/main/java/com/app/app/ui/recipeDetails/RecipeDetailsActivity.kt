package com.app.app.ui.recipeDetails

import android.os.Bundle
import com.app.app.BR
import com.app.app.R
import com.app.app.databinding.ActivityRecipeDetailsBinding
import com.core.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailsActivity :
    BaseActivity<ActivityRecipeDetailsBinding, RecipesDetailsViewModel>() {
    val recipesDetailsViewModel: RecipesDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // retrieve recipe item passed from RecipesListFragment
        //and set it to MutableLiveData variable
        recipesDetailsViewModel.recipesItemUIModel.value = intent.getParcelableExtra(RECIPE_ITEM)
        backButtonListener()
    }

    // handle back click listener
    private fun backButtonListener() {
        viewDataBinding.backArrow.setOnClickListener { onBackPressed() }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.activity_recipe_details

    override fun controllerId(): Int = 0

    override fun getViewModel(): RecipesDetailsViewModel = recipesDetailsViewModel

    companion object {
        const val RECIPE_ITEM = "RECIPE_ITEM"
    }
}