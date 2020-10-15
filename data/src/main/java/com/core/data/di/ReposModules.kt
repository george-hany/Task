package com.core.data.di

import org.koin.dsl.module
import com.core.data.network.ApiFactory
import com.core.data.repos.MainRepo
import com.core.data.repos.RecipesDetailsRepo
import com.core.data.repos.RecipesListRepo
import com.core.data.repos.SplashRepo
import com.core.network.MockedNetwork
import com.core.network.NetworkFactory
import com.core.prefrence.secured_shared_preferences.SecuredSharedPreference
import com.core.prefrence.shared_preferences.AppSharedPreference
import com.core.utils.FileManager

val reposModules = module {

    single { ApiFactory(get()) }
    fun provideMainRepo(
        apiFactory: ApiFactory,
        sharedPreference: SecuredSharedPreference,
        mockedNetwork: MockedNetwork
    ): MainRepo {
        return MainRepo(
            apiFactory,
            sharedPreference,
            mockedNetwork
        )
    }
    factory { provideMainRepo(get(), get(), get()) }

    fun provideSplashRepo(
        apiFactory: ApiFactory,
        sharedPreference: SecuredSharedPreference,
        networkFactory: NetworkFactory
    ): SplashRepo {
        return SplashRepo(apiFactory, sharedPreference, networkFactory)
    }
    factory { provideSplashRepo(get(), get(), get()) }

    fun provideRecipesListRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): RecipesListRepo {
        return RecipesListRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideRecipesListRepo(get(), get(), get(), get()) }

    fun provideRecipesDetailsRepo(
        apiFactory: ApiFactory,
        sharedPreference: AppSharedPreference,
        networkFactory: NetworkFactory,
        fileManager: FileManager
    ): RecipesDetailsRepo {
        return RecipesDetailsRepo(apiFactory, sharedPreference, networkFactory, fileManager)
    }
    factory { provideRecipesDetailsRepo(get(), get(), get(), get()) }
}
