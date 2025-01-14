package com.kom.quotesapp.data.source.network.services

import com.kom.quotesapp.BuildConfig
import com.kom.quotesapp.data.source.network.model.QuoteResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface QuotesApiServices {
    @GET("quotes")
    suspend fun getRandomQuotes(): List<QuoteResponse>

    companion object {
        @JvmStatic
        operator fun invoke(): QuotesApiServices {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL) // sebelum nambah ini build dulu
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(QuotesApiServices::class.java)

        }
    }
}