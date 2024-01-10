package com.palash.retrofitwithmvvm

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.palash.retrofitwithmvvm.api_service.QuoteAPI
import com.palash.retrofitwithmvvm.repository.QuoteRepository
import com.palash.retrofitwithmvvm.retrofit.RetrofitHelper
import com.palash.retrofitwithmvvm.room.QuoteDatabase
import com.palash.retrofitwithmvvm.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class MyApplication : Application() {
    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 30, TimeUnit.MINUTES)
                .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val quoteAPI = RetrofitHelper.getInstance().create(QuoteAPI::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteAPI, database, applicationContext)
    }
}