package com.slavetny.test.presentation.screen.mycurrencies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.slavetny.test.R
import com.slavetny.test.domain.extension.observeNotNull
import com.slavetny.test.presentation.adapter.CurrenciesAdapter
import com.slavetny.test.presentation.adapter.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_mycurrencies.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MyCurrenciesFragment : Fragment(R.layout.fragment_mycurrencies) {

    private val viewModel: MyCurrenciesViewModel by viewModel()
    private val currenciesMap: MutableMap<String, Double> = mutableMapOf()
    private val adapter = CurrenciesAdapter({
        viewModel.deleteCurrency(it)
    }, {
        findNavController().navigate(R.id.action_favorite_to_converter)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()
        setRecyclerView()
    }

    private fun subscribeObservers() {
        viewModel.currenciesLiveData.observeNotNull(requireActivity()) { it ->
            it.rates.forEach {
                currenciesMap.put(
                    Currency.getInstance(it.key).getDisplayName(Locale.getDefault()),
                    it.value
                )
            }

            val filteredMap: MutableMap<String, Double> = mutableMapOf()

            viewModel.dbCurrenciesList.observeNotNull(requireActivity()) {
                it.forEach {
                    filteredMap.put(it, currenciesMap.get(it)!!)
                }

                frg_progress_bar.visibility = View.GONE

                adapter.attachData(filteredMap.toSortedMap())
            }
        }


        viewModel.errorLiveData.observeNotNull(requireActivity()) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView() {
        frg_recycler_view.adapter = adapter
        adapter.isFavorite = true
        frg_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        frg_recycler_view.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        frg_recycler_view.addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.default_margin).toInt()))
    }
}