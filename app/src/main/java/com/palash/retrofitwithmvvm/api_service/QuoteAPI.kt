package com.palash.retrofitwithmvvm.api_service

import com.palash.retrofitwithmvvm.models.QuoteListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET("/quotes")
    suspend fun getQuoteListResponse(@Query("page") page:Int) : Response<QuoteListResponse>
}