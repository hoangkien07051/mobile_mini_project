package com.vmo.mobileminiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vmo.mobileminiproject.navigator.Navigator
import com.vmo.mobileminiproject.ui.DataDiriFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Navigator.replaceFragment(this, R.id.nav_host_fragment, DataDiriFragment())
    }
}