package com.palash.retrofitwithmvvm.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.palash.retrofitwithmvvm.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context, params : WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val repository = (context as MyApplication).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesBackground()
        }
        return Result.success()
    }
}