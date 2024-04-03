package com.kom.quotesapp.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kom.quotesapp.data.model.Quote
import com.kom.quotesapp.databinding.ItemQuoteBinding

/**
Written by Komang Yuda Saputra
Github : https://github.com/YudaSaputraa
 **/
class QuoteListAdapter() : RecyclerView.Adapter<QuoteListAdapter.QuoteItemViewHolder>() {

    private val dataDiffer = AsyncListDiffer(
        this, object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    )

    fun submitData(data: List<Quote>) {
        dataDiffer.submitList(data)

    }

    class QuoteItemViewHolder(
        private val binding: ItemQuoteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Quote) {
            with(item) {
                binding.tvAnime.text = item.anime
                binding.tvQuote.text = item.quote
                binding.tvCharacter.text = "[ ${item.character} ]"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteItemViewHolder(binding)
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        holder.bind(dataDiffer.currentList[position])
    }
}

