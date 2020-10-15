package com.core.data.repos

import androidx.lifecycle.LiveData
import com.core.data.MainExceptions
import com.core.data.base.BaseRepo
import com.core.data.constant.SharedPrefKeys.Companion.SORT_TYPE
import com.core.data.model.recipesList.RecipesListResponseItem
import com.core.data.network.ApiFactory
import com.core.data.network.NetworkBoundFileResource
import com.core.data.strategy.DataStrategy
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference
import com.core.utils.FileManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

class RecipesListRepo(
    val apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface,
    val fileManager: FileManager
) : BaseRepo(sharedPreferences, networkFactory) {

    // make call api to get recipes list
    fun getRecipesList(): LiveData<List<RecipesListResponseItem>> {
        return object : NetworkBoundFileResource<List<RecipesListResponseItem>>(
            networkFactory,
            DataStrategy.Strategies.NETWORK_ONLY,
            fileName = "recipesList",
            fileManager = fileManager
        ) {
            override fun convert(json: String): List<RecipesListResponseItem>? {
                return Gson().fromJson(
                    json,
                    object : TypeToken<List<RecipesListResponseItem>>() {}.type
                )
            }

            override fun onFetchFailed(exception: MainExceptions) {
                exceptionMessage.value = exception.exception
            }

            override suspend fun createCall(): suspend () -> Response<List<RecipesListResponseItem>> =
                {
                    apiFactory.getApisInterface().getRecipesList().await()
                }

            override fun handleErrorResponseType(response: Response<List<RecipesListResponseItem>>) {
                TODO("Not yet implemented")
            }
        }.asLiveData()
    }

    // function to save sort type in shared preference
    fun saveSortTypeToSharedPref(type: String) {
        sharedPreference.saveString(SORT_TYPE, type)
    }

    // function to get sort type from shared preference
    fun getSortTypeFromSharedPref() = sharedPreference.getString(SORT_TYPE)
}