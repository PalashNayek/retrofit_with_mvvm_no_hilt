package com.palash.retrofitwithmvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes_tb")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val quoteId : Int,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
)