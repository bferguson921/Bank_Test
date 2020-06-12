package com.example.banktest.inject

import com.example.banktest.model.PostRepo
import com.example.banktest.model.PostRepoImpl
import com.example.banktest.network.ApiService

class Injection {
    private var userRestService: ApiService? = null
    fun provideUserRepo(): PostRepo {
        return PostRepoImpl(provideUrbanRestService())
    }

    private fun provideUrbanRestService(): ApiService {
        if (userRestService == null) {
            userRestService = ApiService.instance
        }
        return userRestService as ApiService
    }
}