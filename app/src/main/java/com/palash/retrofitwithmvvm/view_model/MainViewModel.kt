package com.palash.retrofitwithmvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.retrofitwithmvvm.models.QuoteListResponse
import com.palash.retrofitwithmvvm.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuotes(1)
        }
    }

    val quoteLiveDataFromViewModel : LiveData<QuoteListResponse> get() = quoteRepository.quoteResultLiveDataRepository

}