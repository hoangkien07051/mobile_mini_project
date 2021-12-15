package com.vmo.mobileminiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.vmo.mobileminiproject.navigator.Navigator
import com.vmo.mobileminiproject.ui.DataDiriFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Navigator.replaceFragment(this, R.id.nav_host_fragment, DataDiriFragment())
    }

    fun showLoading() {
        viewLoading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        viewLoading.visibility = View.GONE
    }
}