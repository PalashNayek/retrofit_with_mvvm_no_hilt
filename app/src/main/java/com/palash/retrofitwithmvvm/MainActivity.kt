package com.palash.retrofitwithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.palash.retrofitwithmvvm.api_service.QuoteAPI
import com.palash.retrofitwithmvvm.repository.QuoteRepository
import com.palash.retrofitwithmvvm.retrofit.RetrofitHelper
import com.palash.retrofitwithmvvm.view_model.MainViewModel
import com.palash.retrofitwithmvvm.view_model.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as MyApplication).quoteRepository
        mainViewModel = ViewModelProvider(this, ViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.quoteLiveDataFromViewModel.observe(this, Observer {
            Log.d("MyResult", "${it.results}")
            Toast.makeText(this@MainActivity, it.results.size.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}