package com.kom.quotesapp.data.datasource

import com.kom.quotesapp.data.source.network.model.QuoteResponse
import com.kom.quotesapp.data.source.network.services.QuotesApiServices

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
interface QuoteDataSource {
    suspend fun getRandomQuotes(): List<QuoteResponse>
}

class QuoteApiDataSource(private val services: QuotesApiServices) : QuoteDataSource {
    override suspend fun getRandomQuotes(): List<QuoteResponse> {
        return services.getRandomQuotes()
    }

}