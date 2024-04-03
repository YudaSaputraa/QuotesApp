package com.kom.quotesapp.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catnip.kokomputer.utils.GenericViewModelFactory
import com.catnip.kokomputer.utils.proceedWhen
import com.kom.quotesapp.R
import com.kom.quotesapp.data.datasource.QuoteApiDataSource
import com.kom.quotesapp.data.datasource.QuoteDataSource
import com.kom.quotesapp.data.repository.QuoteRepository
import com.kom.quotesapp.data.repository.QuoteRepositoryImpl
import com.kom.quotesapp.data.source.network.services.QuotesApiServices
import com.kom.quotesapp.databinding.ActivityMainBinding
import com.kom.quotesapp.presentation.main.adapter.QuoteListAdapter

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels {
        val s = QuotesApiServices.invoke()
        val ds: QuoteDataSource = QuoteApiDataSource(s)
        val rp: QuoteRepository = QuoteRepositoryImpl(ds)
        GenericViewModelFactory.create(MainViewModel(rp))
    }

    private val adapter: QuoteListAdapter by lazy {
        QuoteListAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setQuoteList()
        observeData()


    }

    private fun observeData() {
        viewModel.quotesData.observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    binding.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.rvQuote.isVisible = true
                    result.payload?.let { quote ->
                        adapter.submitData(quote)
                    }

                },
                doOnLoading = {
                    binding.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = true
                    binding.rvQuote.isVisible = false


                },
                doOnEmpty = {
                    binding.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.rvQuote.isVisible = false
                    binding.layoutState.tvOnError.text = getString(R.string.text_empty)

                },
                doOnError = {
                    binding.root.isVisible = true
                    binding.layoutState.pbLoading.isVisible = false
                    binding.rvQuote.isVisible = false
                    binding.layoutState.tvOnError.text = result.exception?.message.orEmpty()
                }

            )

        }
    }

    private fun setQuoteList() {
        binding.rvQuote.adapter = adapter
    }
}