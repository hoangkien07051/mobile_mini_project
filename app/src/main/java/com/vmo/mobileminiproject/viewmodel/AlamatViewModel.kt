package com.vmo.mobileminiproject.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.*
import com.vmo.mobileminiproject.AuthRepo
import com.vmo.mobileminiproject.RetrofitBuilder
import com.vmo.mobileminiproject.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class AlamatViewModel(): ViewModel()  {
    var liveDataMerger = MediatorLiveData<Boolean>()

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