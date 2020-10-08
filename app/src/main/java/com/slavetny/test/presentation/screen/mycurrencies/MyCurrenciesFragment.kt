package com.slavetny.test.presentation.screen.mycurrencies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.slavetny.test.R
import com.slavetny.test.presentation.adapter.CurrenciesAdapter
import com.slavetny.test.presentation.adapter.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_allcurrencies.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MyCurrenciesFragment : Fragment(R.layout.fragment_mycurrencies) {

    private val viewModel: MyCurrenciesViewModel by viewModel()
    private val currenciesMap: MutableMap<String, Double> = mutableMapOf()
    private val adapter = CurrenciesAdapter { status, name ->
        viewModel.deleteCurrency(name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        setRecyclerView()
    }

    private fun subscribeObservers() {
        viewModel.currenciesLiveData.observe(requireActivity(), androidx.lifecycle.Observer { it ->
            it.rates.forEach {
                currenciesMap.put(
                    Currency.getInstance(it.key).getDisplayName(Locale.getDefault()),
                    it.value
                )
            }

            val filteredMap: MutableMap<String, Double> = mutableMapOf()

            viewModel.dbCurrenciesList.observe(requireActivity(), androidx.lifecycle.Observer {
                it.forEach {
                    filteredMap.put(it, currenciesMap.get(it)!!)
                }

                adapter.attachData(filteredMap.toSortedMap())
            })
        })


        viewModel.errorLiveData.observe(requireActivity(), androidx.lifecycle.Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setRecyclerView() {
        frg_recyclerView.adapter = adapter
        frg_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        frg_recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        frg_recyclerView.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_margin).toInt()))
        LinearSnapHelper().attachToRecyclerView(frg_recyclerView)
    }
}