package com.palash.retrofitwithmvvm.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.palash.retrofitwithmvvm.models.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes : List<Result>)

    @Query("SELECT * FROM quotes_tb")
    suspend fun getQuotes() : List<Result>
}