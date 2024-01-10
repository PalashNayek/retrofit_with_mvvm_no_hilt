package com.palash.retrofitwithmvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.retrofitwithmvvm.Utils.NetworkUtils
import com.palash.retrofitwithmvvm.api_service.QuoteAPI
import com.palash.retrofitwithmvvm.models.QuoteListResponse
import com.palash.retrofitwithmvvm.room.QuoteDatabase

class QuoteRepository(
    private val quoteAPI: QuoteAPI,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quoteMutableData = MutableLiveData<QuoteListResponse>()
    val quoteResultLiveDataRepository : LiveData<QuoteListResponse> get() = quoteMutableData

    suspend fun getQuotes(page : Int){

        if (NetworkUtils.isInternetAvailable(applicationContext)){
            val result = quoteAPI.getQuoteListResponse(page)
            if (result.body() != null){
                quoteMutableData.postValue(result.body())
                //add value in database
                quoteDatabase.quoteDao().add(result.body()!!.results)

            }
        }else{
            val quote = quoteDatabase.quoteDao().getQuotes()
            val quoteListResponse = QuoteListResponse(1,1,1, quote,1,1)
            quoteMutableData.postValue(quoteListResponse)
        }
    }
}