package com.example.banktest.model

import io.reactivex.Single

interface PostRepo {
    fun getDefinitionList(): Single<MutableList<Post>>
}