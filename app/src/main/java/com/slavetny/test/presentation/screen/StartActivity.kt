package com.slavetny.test.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.slavetny.test.R
import kotlinx.android.synthetic.main.activity_start.*
import java.util.*

class StartActivity : AppCompatActivity(R.layout.activity_start) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Toast.makeText(this, Currency.getInstance("RUB").getDisplayName(Locale.getDefault()), Toast.LENGTH_SHORT).show()

        NavigationUI.setupWithNavController(
            bottom_navigation_view,
            findNavController(R.id.nav_host_fragment)
        )
    }
}