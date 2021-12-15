package com.vmo.mobileminiproject.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.vmo.mobileminiproject.AuthRepo
import com.vmo.mobileminiproject.RetrofitBuilder
import com.vmo.mobileminiproject.utils.Resource
import kotlinx.coroutines.Dispatchers

@SuppressLint("StaticFieldLeak")
class AlamatViewModel(): ViewModel()  {

    var isDomicileAddress = MutableLiveData(false)
    var isHousingType = MutableLiveData(false)
    var isNo = MutableLiveData(false)
    var isProvince = MutableLiveData(false)

    var liveDataMerger = MediatorLiveData<Boolean>()

    init {
        liveDataMerger.apply {
            addSource(isDomicileAddress) {
                liveDataMerger.value = enableSave()
            }
            addSource(isHousingType) {
                liveDataMerger.value = enableSave()
            }
            addSource(isNo) {
                liveDataMerger.value = enableSave()
            }
            addSource(isProvince) {
                liveDataMerger.value = enableSave()
            }
        }
    }

    private fun enableSave(): Boolean {
        return isDomicileAddress.value == true &&
                isHousingType.value == true &&
                isNo.value == true &&
                isProvince.value == true
    }

    fun requestProvince(url: String) = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.loading(data = null))
        getProvince(url, this)
    }

    private suspend fun getProvince(url: String, liveData: LiveDataScope<Resource<String>>) {
        val authRepo = AuthRepo(RetrofitBuilder().apiService())
        val response = authRepo.getProvince(url)
        if (response.isSuccessful) {
            response.body()?.let {
                liveData.emit(Resource.success(it))
            }
        } else {
            liveData.emit(Resource.error(data = null, msg = "Get Province Error"))
        }
    }
}