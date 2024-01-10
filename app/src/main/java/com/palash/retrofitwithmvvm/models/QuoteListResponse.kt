package com.palash.retrofitwithmvvm.models

data class QuoteListResponse(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)