package com.kom.quotesapp.data.repository

import com.catnip.kokomputer.utils.ResultWrapper
import com.catnip.kokomputer.utils.proceedFlow
import com.kom.quotesapp.data.datasource.QuoteDataSource
import com.kom.quotesapp.data.mapper.toQuotes
import com.kom.quotesapp.data.model.Quote
import kotlinx.coroutines.flow.Flow

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
interface QuoteRepository {
    fun getRandomQuotes(): Flow<ResultWrapper<List<Quote>>>
}

class QuoteRepositoryImpl(private val dataSource: QuoteDataSource) : QuoteRepository {
    override fun getRandomQuotes(): Flow<ResultWrapper<List<Quote>>> {
        return proceedFlow { dataSource.getRandomQuotes().toQuotes() }
    }

}