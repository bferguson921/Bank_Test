package com.example.banktest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banktest.model.Post
import com.example.banktest.model.PostRepo
import io.reactivex.disposables.CompositeDisposable


class MainViewModel constructor(private val postRepo: PostRepo): ViewModel() {
    private val disposable = CompositeDisposable()
    private val stateMutableLiveData = MutableLiveData<AppState>()
    val stateLiveData: LiveData<AppState>
        get() = stateMutableLiveData

    fun getPosts() {
        disposable.add(
            postRepo.getDefinitionList()
                .subscribe({
                    stateMutableLiveData.value = AppState.SUCCESS(it)
                }, {
                    stateMutableLiveData.value = AppState.ERROR("errorString")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    sealed class AppState {
        data class SUCCESS(val postList: MutableList<Post>) : AppState()
        data class ERROR(val message: String) : AppState()
    }
}