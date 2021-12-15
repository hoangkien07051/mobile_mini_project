package com.vmo.mobileminiproject.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
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