package com.kom.quotesapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kom.quotesapp.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
class MainViewModel(private val repository: QuoteRepository) : ViewModel() {
    val quotesData = repository.getRandomQuotes().asLiveData(Dispatchers.IO)
}