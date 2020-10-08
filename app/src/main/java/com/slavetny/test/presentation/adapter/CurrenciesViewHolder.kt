package com.slavetny.test.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.slavetny.test.R
import com.slavetny.test.domain.constants.Constants
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(currencyName: String, isCurrencyInFavorite: Boolean, clickCallback: (status: String, name: String) -> Unit) {
        val status = if (isCurrencyInFavorite)
            Constants.ADD
        else
            Constants.REMOVE

        itemView.currency_name.text = currencyName
        itemView.currency_btn.text = status

        itemView.currency_btn.setOnClickListener {
            clickCallback(status, currencyName)
        }
    }
}