package com.slavetny.test.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.slavetny.test.R
import com.slavetny.test.domain.extension.inflate
import java.util.*

class CurrenciesAdapter(
    private val controllButtonCallback: (name: String) -> Unit,
    private val itemClickedCallback: () -> Unit
) : RecyclerView.Adapter<CurrenciesViewHolder>() {

    private var currenciesList: SortedMap<String, Double> = sortedMapOf()
    var isFavorite: Boolean = false

    fun attachData(currenciesList: SortedMap<String, Double>) {
        this.currenciesList = currenciesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrenciesViewHolder(
            parent.inflate(R.layout.currency_item)
        ) { name, position ->
            if (isFavorite) {
                currenciesList.remove(name)
                notifyItemRemoved(position)
            }
        }

    override fun getItemCount() = currenciesList.size

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(currenciesList.keys.toList()[position], isFavorite, controllButtonCallback, itemClickedCallback)
    }
}