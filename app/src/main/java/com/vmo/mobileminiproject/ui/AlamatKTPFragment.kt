package com.vmo.mobileminiproject.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.vmo.mobileminiproject.MainActivity
import com.vmo.mobileminiproject.R
import com.vmo.mobileminiproject.model.EnumHousingType
import com.vmo.mobileminiproject.model.ProvinceEntity
import com.vmo.mobileminiproject.navigator.Navigator
import com.vmo.mobileminiproject.utils.*
import com.vmo.mobileminiproject.viewmodel.AlamatViewModel
import com.vmo.mobileminiproject.viewmodel.DiriViewModel
import kotlinx.android.synthetic.main.fragment_alamat.*
import kotlinx.android.synthetic.main.fragment_alamat.icon_back

class AlamatKTPFragment : Fragment(R.layout.fragment_alamat) {

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

    fun showLoading() {
        if (activity is MainActivity) {
            (activity as MainActivity).showLoading()
        }
    }

    fun hideLoading() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideLoading()
        }
    }
}