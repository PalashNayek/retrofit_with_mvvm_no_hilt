package com.palash.retrofitwithmvvm

import android.app.Application
import com.palash.retrofitwithmvvm.api_service.QuoteAPI
import com.palash.retrofitwithmvvm.repository.QuoteRepository
import com.palash.retrofitwithmvvm.retrofit.RetrofitHelper
import com.palash.retrofitwithmvvm.room.QuoteDatabase

class MyApplication : Application() {
    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteAPI = RetrofitHelper.getInstance().create(QuoteAPI::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteAPI, database, applicationContext)
    }
}