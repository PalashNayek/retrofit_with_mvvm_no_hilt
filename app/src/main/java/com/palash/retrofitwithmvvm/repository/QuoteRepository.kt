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

    private val quoteMutableData = MutableLiveData<Response<QuoteListResponse>>()
    val quoteResultLiveDataRepository : LiveData<Response<QuoteListResponse>> get() = quoteMutableData

    suspend fun getQuotes(page : Int){

        if (NetworkUtils.isInternetAvailable(applicationContext)){
            val result = quoteAPI.getQuoteListResponse(page)
            try {
                if (result.body() != null){
                    quoteMutableData.postValue(Response.Success(result.body()))
                    //add value in database
                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                }else{
                    quoteMutableData.postValue(Response.Error("API Error!"))
                }
            }catch (e: Exception){
                quoteMutableData.postValue(Response.Error(e.message.toString()))
            }
        }else{
            val quote = quoteDatabase.quoteDao().getQuotes()
            val quoteListResponse = QuoteListResponse(1,1,1, quote,1,1)
            quoteMutableData.postValue(Response.Success(quoteListResponse))
        }
    }

    suspend fun getQuotesBackground(){
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteAPI.getQuoteListResponse(randomNumber)
        if (result.body()!=null){
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
        }

    }
}