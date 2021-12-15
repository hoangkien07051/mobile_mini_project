package com.vmo.mobileminiproject.utils

import android.content.Context
import android.widget.Toast
import com.vmo.mobileminiproject.R

class NetworkUtil {
    companion object {
        fun checkNetwork(context: Context): Boolean {
            return if (context.isHasConnectInternet()) {
                true
            } else {
                showError(context)
                false
            }
        }

        private fun showError(context: Context) {
            Toast.makeText(context, context.getString(R.string.network_error), Toast.LENGTH_LONG).show()
        }
    }
}