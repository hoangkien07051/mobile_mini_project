package com.vmo.mobileminiproject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlamatViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val viewModel by lazy {
        AlamatViewModel()
    }

    private fun setValues(value: Boolean) {
        viewModel.isDomicileAddress.value = value
        viewModel.isHousingType.value = value
        viewModel.isNo.value = value
        viewModel.isProvince.value = value
    }


    @Test
    fun testEnableSave() {
        setValues(false)

        val isEnableSave = viewModel.enableSave()
        Assert.assertEquals(false, isEnableSave)
    }

}