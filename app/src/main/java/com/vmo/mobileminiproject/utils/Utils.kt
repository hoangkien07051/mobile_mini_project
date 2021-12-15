package com.vmo.mobileminiproject.utils

import android.os.Build
import android.text.Html
import android.widget.TextView

class Utils {

    companion object {


        fun setHtmlTextview(textView: TextView, html: String) {
            textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(html)
            }
        }
    }
}