package com.core.data.network

import com.core.data.BuildConfig
import com.core.data.constant.AppConstant
import com.core.data.network.interfaces.ApisInterface
import com.core.network.RetrofitClient

class ApiFactory(var appConstant: AppConstant) {
    fun getApisInterface(): ApisInterface {
        return RetrofitClient
            .retrofit(BuildConfig.BASE_URL)
            .create(ApisInterface::class.java)
    }
}
