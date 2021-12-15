package com.vmo.mobileminiproject.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.vmo.mobileminiproject.R
import com.vmo.mobileminiproject.model.EnumEducation
import com.vmo.mobileminiproject.utils.Constants
import com.vmo.mobileminiproject.utils.Utils
import com.vmo.mobileminiproject.utils.toDefaultDateString
import com.vmo.mobileminiproject.viewmodel.DiriViewModel
import kotlinx.android.synthetic.main.fragment_diri.*
import java.util.*
import javax.inject.Inject


class DataDiriFragment : Fragment(R.layout.fragment_diri) {
    private val calendarBirthDay: Calendar? = Calendar.getInstance()

    private val diriViewModel: DiriViewModel by lazy {
        ViewModelProvider(this).get(DiriViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Utils.setHtmlTextview(national_id_name, "${getString(R.string.national_id)} ${Constants.htmlPropRequired}")
        Utils.setHtmlTextview(bank_account_no_name, "${getString(R.string.bank_account_no)} ${Constants.htmlPropRequired}")
        Utils.setHtmlTextview(education_name, "${getString(R.string.education)} ${Constants.htmlPropRequired}")
        Utils.setHtmlTextview(date_of_birth_name, "${getString(R.string.date_of_birth)} ${Constants.htmlPropRequired}")

        val arrEducation = EnumEducation.values().toMutableList()
        val adapterEducation = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, arrEducation)
        spEducation.adapter = adapterEducation

        handleViewListener()
    }

    private fun handleViewListener() {
        national_id.doAfterTextChanged {
            diriViewModel.isNationalId.postValue(it.toString().trim().isNotEmpty())
        }
        fullname.doAfterTextChanged {
            diriViewModel.isFullname.postValue(it.toString().trim().isNotEmpty())
        }
        bank_account_no_name.doAfterTextChanged {
            val count = it.toString().trim().length
            if(count <= 8) {
                diriViewModel.isBankAccountNo.postValue(false)
            } else {
                diriViewModel.isBankAccountNo.postValue(true)
            }
        }

        spEducation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                diriViewModel.isEducation.postValue(true)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendarBirthDay?.let {
                it.set(Calendar.YEAR, year)
                it.set(Calendar.MONTH, monthOfYear)
                it.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
            tvTime.text = calendarBirthDay?.time?.toDefaultDateString()
            diriViewModel.isDateOfBirth.postValue(true)
            view
        }
        date_of_birth.setOnClickListener {
            calendarBirthDay?.let {
                val picker = DatePickerDialog(requireContext(), date, calendarBirthDay.get(Calendar.YEAR),
                    calendarBirthDay.get(Calendar.MONTH), calendarBirthDay[Calendar.DATE])
                picker.datePicker.maxDate = calendarBirthDay.timeInMillis
                picker.show()
            }
        }

        diriViewModel.liveDataMerger.observe(viewLifecycleOwner, {
            btnSave.isEnabled = it
        })
    }


}