package com.example.banktest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.banktest.model.PostRepo

class MainViewModelFactory(private val postRepo: PostRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(postRepo) as T
    }
}