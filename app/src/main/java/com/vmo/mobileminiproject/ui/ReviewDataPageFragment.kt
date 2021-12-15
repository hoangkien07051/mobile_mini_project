package com.vmo.mobileminiproject.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vmo.mobileminiproject.R
import com.vmo.mobileminiproject.model.DiriData
import com.vmo.mobileminiproject.navigator.Navigator
import com.vmo.mobileminiproject.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_alamat.*
import kotlinx.android.synthetic.main.fragment_review_data.*
import kotlinx.android.synthetic.main.fragment_review_data.icon_back

private const val DIRI_DATA = "diri_data"

class ReviewDataPageFragment: Fragment(R.layout.fragment_review_data) {

    companion object {
        @JvmStatic
        fun newInstance(diri: DiriData) = ReviewDataPageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DIRI_DATA, diri)
            }
        }
    }

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

        var diriData: DiriData? = null
        arguments?.let {
            diriData = it.getParcelable(DIRI_DATA)
        }

        national_id_value.text = diriData?.nationalId
        fullname_value.text = diriData?.fullName
        bank_account_no_value.text = diriData?.bankAccountNo
        education_value.text = diriData?.education
        date_of_birth_value.text = diriData?.dateOfBirth
        domicile_address_value.text = diriData?.domicileAddress
        housing_type_value.text = diriData?.housingType
        no_value.text = diriData?.no
        province_value.text = diriData?.province

    }
}