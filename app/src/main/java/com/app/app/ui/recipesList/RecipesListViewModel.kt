package com.app.app.ui.recipesList

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.app.app.ui.recipesList.model.RecipesItemUIModel
import com.core.base.BaseViewModel
import com.core.data.repos.RecipesListRepo
import com.core.utils.AppConstant.CALORIES
import com.core.utils.AppConstant.FATS
import java.util.*

class RecipesListViewModel(var recipesListRepo: RecipesListRepo) :
    BaseViewModel<RecipesListRepo>(recipesListRepo) {
    val recipesListMediatorLiveData = MediatorLiveData<Any>()
    val recipesItemListUIModelLiveData = MutableLiveData<List<RecipesItemUIModel>>()
    var recipesItemListUIModel = listOf<RecipesItemUIModel>()
    var sortType = recipesListRepo.getSortTypeFromSharedPref()

    init {
        getRecipesList()
    }

    // request recipes list from repository
    private fun getRecipesList() {
        val requestRecipesList = recipesListRepo.getRecipesList()
        recipesListMediatorLiveData.addSource(requestRecipesList) { res ->
            recipesItemListUIModel =
                res.map { RecipesItemUIModel.convertResponseToUI(it) }
            recipesItemListUIModelLiveData.value = recipesItemListUIModel
            checkPreviousSort()
        }
    }

    // check if there is previous sort stored in shared preference
    private fun checkPreviousSort() {
        when (sortType) {
            CALORIES -> sortByCalories()
            FATS -> sortByFats()
        }
    }

    // apply search query by name
    fun search(query: String) {
        recipesItemListUIModelLiveData.value =
            recipesItemListUIModel.filter { it.name!!.toLowerCase(Locale.ROOT).contains(query) }
    }

    // sort the list by calories and save sort type as calories
    fun sortByCalories() {
        if (recipesItemListUIModelLiveData.value.isNullOrEmpty())
            return
        recipesItemListUIModel = recipesItemListUIModel.filter { it.fats!!.isNotEmpty() }
            .sortedWith(compareBy { it.calories!!.split(" ")[0].toInt() })

        recipesItemListUIModelLiveData.value =
            recipesItemListUIModelLiveData.value?.filter { it.fats!!.isNotEmpty() }
                ?.sortedWith(compareBy { it.calories!!.split(" ")[0].toInt() })

        recipesListRepo.saveSortTypeToSharedPref(CALORIES)
    }

    // sort the list by fats and save sort type as fats
    fun sortByFats() {
        if (recipesItemListUIModelLiveData.value.isNullOrEmpty())
            return
        recipesItemListUIModel = recipesItemListUIModel.filter { it.fats!!.isNotEmpty() }
            .sortedWith(compareBy { it.fats!!.split(" ")[0].toInt() })

        recipesItemListUIModelLiveData.value =
            recipesItemListUIModelLiveData.value?.filter { it.fats!!.isNotEmpty() }
                ?.sortedWith(compareBy { it.fats!!.split(" ")[0].toInt() })

        recipesListRepo.saveSortTypeToSharedPref(FATS)
    }
}