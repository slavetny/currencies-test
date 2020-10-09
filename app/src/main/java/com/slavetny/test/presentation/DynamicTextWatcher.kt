package com.slavetny.test.presentation

import android.text.Editable
import android.text.TextWatcher

class DynamicTextWatcher(
    private val afterChanged: ((Editable?) -> Unit)? = {},
    private val beforeChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null,
    private val onChanged: ((CharSequence?, Int, Int, Int) -> Unit)? = null
) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        afterChanged?.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged?.invoke(s, start, before, count)
    }
}