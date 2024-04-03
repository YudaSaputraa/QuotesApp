package com.kom.quotesapp.data.mapper

import com.kom.quotesapp.data.model.Quote
import com.kom.quotesapp.data.source.network.model.QuoteResponse

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/

fun QuoteResponse.toQuote() = Quote(
    id = this.id.orEmpty(),
    quote = this.quote.orEmpty(),
    anime = this.anime.orEmpty(),
    character = this.character.orEmpty()
)

fun Collection<QuoteResponse>.toQuotes() = this.map {
    it.toQuote()
}