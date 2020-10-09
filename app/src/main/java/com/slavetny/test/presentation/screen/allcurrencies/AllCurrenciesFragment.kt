package com.slavetny.test.presentation.screen.allcurrencies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.slavetny.test.R
import com.slavetny.test.domain.extension.observeNotNull
import com.slavetny.test.presentation.adapter.CurrenciesAdapter
import com.slavetny.test.presentation.DynamicTextWatcher
import com.slavetny.test.presentation.adapter.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_allcurrencies.*
import kotlinx.android.synthetic.main.fragment_mycurrencies.frg_recycler_view
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class AllCurrenciesFragment : Fragment(R.layout.fragment_allcurrencies) {

    private val viewModel: AllCurrenciesViewModel by viewModel()
    private val currenciesMap: MutableMap<String, Double> = mutableMapOf()
    private val adapter = CurrenciesAdapter({
        viewModel.insertCurrency(it)
    }, {
        findNavController().navigate(R.id.action_currencies_to_converter)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        setRecyclerView()
        search()
    }

    private fun search() {
        frg_edit_text.addTextChangedListener(DynamicTextWatcher(
                onChanged = { p0, _, _, _ ->
                    if (!p0.isNullOrBlank()) {

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
        viewModel.currenciesLiveData.observeNotNull(requireActivity()) { it ->
            it.rates.forEach {
                currenciesMap.put(
                    Currency.getInstance(it.key).getDisplayName(Locale.getDefault()),
                    it.value
                )
            }

            frg_progress_bar.visibility = View.GONE

            adapter.attachData(currenciesMap.toSortedMap())
        }

        viewModel.errorLiveData.observeNotNull(requireActivity()) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView() {
        frg_recycler_view.adapter = adapter
        adapter.isFavorite = false
        frg_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        frg_recycler_view.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        frg_recycler_view.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_margin).toInt()))
    }
}