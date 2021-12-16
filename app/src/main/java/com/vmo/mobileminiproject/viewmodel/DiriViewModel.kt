package com.vmo.mobileminiproject.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DiriViewModel @Inject constructor() : ViewModel() {
    var isNationalId = MutableLiveData(false)
    var isFullname = MutableLiveData(false)
    var isBankAccountNo = MutableLiveData(false)
    var isEducation = MutableLiveData(false)
    var isDateOfBirth = MutableLiveData(false)

    var liveDataMerger = MediatorLiveData<Boolean>()

    init {
        liveDataMerger.apply {
            addSource(isNationalId) {
                liveDataMerger.value = enableSave()
            }
            addSource(isFullname) {
                liveDataMerger.value = enableSave()
            }
            addSource(isBankAccountNo) {
                liveDataMerger.value = enableSave()
            }
            addSource(isEducation) {
                liveDataMerger.value = enableSave()
            }
            addSource(isDateOfBirth) {
                liveDataMerger.value = enableSave()
            }
        }
    }

    fun enableSave(): Boolean {
        return isNationalId.value == true &&
                isFullname.value == true &&
                isBankAccountNo.value == true &&
                isEducation.value == true &&
                isDateOfBirth.value == true
    }
}