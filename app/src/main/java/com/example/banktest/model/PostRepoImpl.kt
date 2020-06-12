package com.example.banktest.model

import com.example.banktest.network.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


open class PostRepoImpl(private val postService: ApiService) : PostRepo {
    override fun getDefinitionList(): Single<MutableList<Post>> {
        return postService
            .getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}