package com.vmo.mobileminiproject.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vmo.mobileminiproject.R
import com.vmo.mobileminiproject.navigator.Navigator
import com.vmo.mobileminiproject.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_alamat.*

class AlamatKTPFragment : Fragment(R.layout.fragment_alamat) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigator.backFragment(context as AppCompatActivity)
                view.hideKeyboard()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        icon_back.setOnClickListener {
            activity?.onBackPressed()
            view.hideKeyboard()
        }

    }
}