package com.vmo.mobileminiproject.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.vmo.mobileminiproject.MainActivity
import com.vmo.mobileminiproject.R
import com.vmo.mobileminiproject.model.DiriData
import com.vmo.mobileminiproject.model.EnumHousingType
import com.vmo.mobileminiproject.model.ProvinceEntity
import com.vmo.mobileminiproject.navigator.Navigator
import com.vmo.mobileminiproject.utils.*
import com.vmo.mobileminiproject.viewmodel.AlamatViewModel
import kotlinx.android.synthetic.main.fragment_alamat.*
import kotlinx.android.synthetic.main.fragment_alamat.btnSave
import kotlinx.android.synthetic.main.fragment_alamat.icon_back
import kotlinx.android.synthetic.main.fragment_diri.*

private const val DIRI_DATA = "diri_data"

class AlamatKTPFragment : Fragment(R.layout.fragment_alamat) {

    companion object {
        @JvmStatic
        fun newInstance(diri: DiriData) = AlamatKTPFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DIRI_DATA, diri)
            }
        }
    }

    private val alamatViewModel: AlamatViewModel by lazy {
        ViewModelProvider(this).get(AlamatViewModel::class.java)
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

        Utils.setHtmlTextview(domicile_address_name, "${getString(R.string.domicile_address)} ${Constants.htmlPropRequired}")
        Utils.setHtmlTextview(housing_type_name, "${getString(R.string.housing_type)} ${Constants.htmlPropRequired}")
        Utils.setHtmlTextview(no_name, "${getString(R.string.no)} ${Constants.htmlPropRequired}")
        Utils.setHtmlTextview(province_name, "${getString(R.string.province)} ${Constants.htmlPropRequired}")

        val arrHousingType = mutableListOf<String>()
        EnumHousingType.values().forEach {
            arrHousingType.add(it.value)
        }
        val adapterHousingType = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, arrHousingType)
        spHousingType.adapter = adapterHousingType

        requestProvince()
        handleViewListener()
    }

    private fun handleViewListener() {
        domicile_address.doAfterTextChanged {
            alamatViewModel.isDomicileAddress.postValue(true)
        }
        no.doAfterTextChanged {
            alamatViewModel.isNo.postValue(true)
        }

        spHousingType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                view?.hideKeyboard()
                alamatViewModel.isHousingType.postValue(true)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        spProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                view?.hideKeyboard()
                alamatViewModel.isProvince.postValue(true)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        alamatViewModel.liveDataMerger.observe(viewLifecycleOwner, {
            btnSave.isEnabled = it
        })

        btnSave.setOnClickListener {
            var diriData: DiriData? = null
            arguments?.let {
                diriData = it.getParcelable(DIRI_DATA)
            }
            if(diriData != null) {
                Navigator.addFragment(this, R.id.nav_host_fragment, ReviewDataPageFragment.newInstance(
                    diriData!!
                ))
            }
        }
    }

    private fun requestProvince() {
        if (!NetworkUtil.checkNetwork(requireContext())) return
        val url = "https://ibnux.github.io/data-indonesia/provinsi.json"
        alamatViewModel.requestProvince(url).observe(this.viewLifecycleOwner, {
            it?.let { its ->
                when(its.status) {
                    Status.LOADING -> showLoading()
                    Status.ERROR -> {
                        hideLoading()
                        Toast.makeText(requireActivity(), its.toString(), Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        hideLoading()
                        val arrProvince = Gson().fromJson(its.data, Array<ProvinceEntity>::class.java)
                        val arrNama = mutableListOf<String>()
                        arrProvince.forEach { itp ->
                                arrNama.add(itp.nama)
                        }
                        val adapterProvince = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, arrNama)
                        spProvince.adapter = adapterProvince
                    }
                }
            }
        })
    }

    private fun showLoading() {
        if (activity is MainActivity) {
            (activity as MainActivity).showLoading()
        }
    }

    private fun hideLoading() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideLoading()
        }
    }
}