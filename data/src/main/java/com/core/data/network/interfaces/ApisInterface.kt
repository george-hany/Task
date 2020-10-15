package com.core.data.network.interfaces

import com.core.data.model.recipesList.RecipesListResponseItem
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApisInterface {
    @GET("android-test/recipes.json")
    fun getRecipesList(): Deferred<Response<List<RecipesListResponseItem>>>
}