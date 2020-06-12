package com.example.banktest.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banktest.R
import com.example.banktest.adapter.PostAdapter
import com.example.banktest.inject.Injection
import com.example.banktest.model.Post
import com.example.banktest.viewmodel.MainViewModel
import com.example.banktest.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    var postAdapter: PostAdapter = PostAdapter()
    val injection = Injection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(injection.provideUserRepo())
        ).get(MainViewModel::class.java)

        mainViewModel.stateLiveData.observe(this, Observer { appState ->
            when (appState) {
                is MainViewModel.AppState.SUCCESS -> displayPost(appState.postList)
                is MainViewModel.AppState.ERROR -> displayError(appState.message)
                else -> displayError("Something Went Wrong... Try Again.")
            }
        })

        initRecyclerView()
        mainViewModel.getPosts()
    }

    private fun displayPost(wordsList: MutableList<Post>) {
        // set recycler to eliminate flicker
        postAdapter.update(wordsList)
    }

    private fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initRecyclerView() {
        post_list.layoutManager = LinearLayoutManager(this)
        post_list.adapter = postAdapter
    }
}
