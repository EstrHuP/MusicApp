package com.example.musicapp.utils

import android.text.TextUtils
import java.util.regex.Pattern


class Utils {
    private val EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    fun validate(email: String): Boolean {
        if (!TextUtils.isEmpty(email)) {
            val pattern = Pattern.compile(EMAIL_PATTERN)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
        return false
    }
}