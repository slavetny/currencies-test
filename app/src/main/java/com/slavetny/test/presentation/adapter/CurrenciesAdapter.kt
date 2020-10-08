package com.slavetny.test.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slavetny.test.R
import java.util.*

class CurrenciesAdapter(
    private val clickCallback: (status: String, name: String) -> Unit
) : RecyclerView.Adapter<CurrenciesViewHolder>() {

    private var currenciesList: SortedMap<String, Double> = sortedMapOf()

    fun attachData(currenciesList: SortedMap<String, Double>) {
        this.currenciesList = currenciesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrenciesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        )

    override fun getItemCount() = currenciesList.size

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(currenciesList.keys.toList()[position], isCurrencyInFavorite(position), clickCallback)
    }

    private fun isCurrencyInFavorite(position: Int) = true
}