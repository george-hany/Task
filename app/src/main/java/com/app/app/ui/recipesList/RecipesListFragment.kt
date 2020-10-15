package com.app.app.ui.recipesList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.app.app.BR
import com.app.app.R
import com.app.app.databinding.FragmentRecipesListBinding
import com.app.app.ui.recipeDetails.RecipeDetailsActivity
import com.app.app.ui.recipesList.adapter.RecipesListAdapter
import com.app.app.ui.recipesList.model.RecipesItemUIModel
import com.core.base.BaseFragment
import com.core.utils.AppConstant
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class RecipesListFragment : BaseFragment<FragmentRecipesListBinding, RecipesListViewModel>() {
    val recipesListViewModel: RecipesListViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipesListMediatorLiveDataObserver()
        setupRecipesListRecycler()
        recipesItemListUIModelObserver()
        searchBarListener()
        setupSortRadioGroup()
    }

    // setup radio buttons
    // check if there is previous sort
    // add Listener to radio group
    private fun setupSortRadioGroup() {
        viewDataBinding.run {
            when (recipesListViewModel.sortType) {
                AppConstant.CALORIES -> calories.isChecked = true
                AppConstant.FATS -> fats.isChecked = true
            }
            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.calories -> recipesListViewModel.sortByCalories()
                    R.id.fats -> recipesListViewModel.sortByFats()
                }
            }
        }
    }

    // observe on recipesItemListUIModelLiveData to update adapter when changes are happened
    private fun recipesItemListUIModelObserver() {
        recipesListViewModel.recipesItemListUIModelLiveData.observe(viewLifecycleOwner, Observer {
            viewDataBinding.adapter?.run {
                recipesList.clear()
                recipesList.addAll(it)
                notifyDataSetChanged()
            }
        })
    }

    // setup recycler view
    // initialize the adapter
    // start details activity when item clicked
    private fun setupRecipesListRecycler() {
        viewDataBinding.run {
            adapter = RecipesListAdapter(ArrayList())
            adapter?.clickListener = object : RecipesListAdapter.ClickListener {
                override fun onClick(model: RecipesItemUIModel) {
                    val intent = Intent(requireContext(), RecipeDetailsActivity::class.java)
                    intent.putExtra(RecipeDetailsActivity.RECIPE_ITEM, model)
                    startActivity(intent)
                }
            }
        }
    }

    // add lifecycle to recipesListMediatorLiveData
    private fun recipesListMediatorLiveDataObserver() {
        recipesListViewModel.recipesListMediatorLiveData.observe(viewLifecycleOwner, Observer {})
    }

    // add setOnQueryTextListener to searchView 
    private fun searchBarListener() {
        viewDataBinding.run {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.toLowerCase(Locale.ROOT)?.let {
                        recipesListViewModel.search(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.toLowerCase(Locale.ROOT)?.let {
                        recipesListViewModel.search(it)
                    }
                    return true
                }
            })
        }
    }

    override fun bindingVariable(): Int = BR.viewModel

    override fun layoutId(): Int = R.layout.fragment_recipes_list

    override fun getViewModel(): RecipesListViewModel = recipesListViewModel

}