package com.slavetny.test.presentation.screen.allcurrencies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import com.slavetny.test.R
import com.slavetny.test.domain.constants.Constants
import com.slavetny.test.presentation.adapter.CurrenciesAdapter
import com.slavetny.test.presentation.screen.DynamicTextWatcher
import com.slavetny.test.presentation.adapter.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_allcurrencies.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class AllCurrenciesFragment : Fragment(R.layout.fragment_allcurrencies) {

    private val viewModel: AllCurrenciesViewModel by viewModel()
    private val currenciesMap: MutableMap<String, Double> = mutableMapOf()
    private val adapter = CurrenciesAdapter { status, name ->
        when (status) {
            Constants.ADD -> viewModel.insertCurrency(name)
            Constants.REMOVE -> viewModel.deleteCurrency(name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        setRecyclerView()
        search()
    }

    private fun search() {
        frg_editText.addTextChangedListener(DynamicTextWatcher(
            onChanged = { p0, _, _, _ ->
                if (p0.toString() != "") {

                    val filteredMap: MutableMap<String, Double> = mutableMapOf()

                    currenciesMap.forEach {
                        if (it.key.toLowerCase().contains(p0.toString().toLowerCase())) {
                            filteredMap.put(it.key, it.value)
                        }
                    }

                    adapter.attachData(filteredMap.toSortedMap())
                } else {
                    adapter.attachData(currenciesMap.toSortedMap())
                }
            }
        ))
    }

    private fun subscribeObservers() {
        viewModel.currenciesLiveData.observe(requireActivity(), Observer { it ->
            it.rates.forEach {
                currenciesMap.put(
                    Currency.getInstance(it.key).getDisplayName(Locale.getDefault()),
                    it.value
                )
            }

            adapter.attachData(currenciesMap.toSortedMap())
        })

        viewModel.errorLiveData.observe(requireActivity(), Observer {
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