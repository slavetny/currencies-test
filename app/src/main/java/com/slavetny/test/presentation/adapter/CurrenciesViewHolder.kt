package com.slavetny.test.presentation.adapter

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.slavetny.test.R
import com.slavetny.test.domain.constants.Constants
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrenciesViewHolder(
    itemView: View,
    private val currencyButtonCallback: (name: String, position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    fun bind(currencyName: String, isFavorite: Boolean, clickCallback: (name: String) -> Unit, itemClickedCallback: () -> Unit) {
        val status = if (isFavorite)
            Constants.REMOVE
        else
            Constants.ADD

        itemView.currency_name.text = currencyName
        itemView.currency_btn.text = status

        itemView.setOnClickListener {
            itemClickedCallback()
        }

        itemView.currency_btn.setOnClickListener {
            clickCallback(currencyName)
            currencyButtonCallback(currencyName, adapterPosition)
        }
    }
}