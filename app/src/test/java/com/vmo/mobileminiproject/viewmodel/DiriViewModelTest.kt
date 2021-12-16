package com.vmo.mobileminiproject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DiriViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val viewModel by lazy {
        DiriViewModel()
    }

    private fun setValues(value: Boolean) {
        viewModel.isNationalId.value = value
        viewModel.isFullname.value = value
        viewModel.isEducation.value = value
        viewModel.isBankAccountNo.value = value
        viewModel.isDateOfBirth.value = value
    }

    @Test
    fun testEnableSave() {
        setValues(false)

        val isEnableSave = viewModel.enableSave()
        Assert.assertEquals(false, isEnableSave)
    }
}
