package com.slavetny.test.presentation.screen.converter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.slavetny.test.R
import com.slavetny.test.domain.extension.observeNotNull
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.fragment_converter.*
import org.koin.android.viewmodel.ext.android.viewModel

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private val viewModel: ConverterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.bottom_navigation_view?.visibility = View.GONE

        frg_converter_btn.setOnClickListener {
            viewModel.getConvertedValue(
                "USD",
                "EUR",
                frg_converter_edit_text.text.toString().toInt()
            )
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.convertedCurrencyLiveData.observeNotNull(requireActivity()) {
            frg_result_text_view.text = it.result.toString()

        }

        viewModel.errorLiveData.observeNotNull(requireActivity()) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        activity?.bottom_navigation_view?.visibility = View.VISIBLE
    }
}