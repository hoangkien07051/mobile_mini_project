package com.vmo.mobileminiproject.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.vmo.mobileminiproject.R
import com.vmo.mobileminiproject.model.DiriData

private const val DIRI_DATA = "diri_data"

class ReviewDataPageFragment: Fragment(R.layout.fragment_review_data) {

    companion object {
        @JvmStatic
        fun newInstance(diri: DiriData) = AlamatKTPFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DIRI_DATA, diri)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var diriData: DiriData? = null
        arguments?.let {
            diriData = it.getParcelable(DIRI_DATA)
        }

    }
}