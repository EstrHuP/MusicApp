package com.example.musicapp.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import com.robertlevonyan.components.picker.Str
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


object Utils {
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

    fun formatStringDate(date: Date):String{
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(date)
    }

//    fun formatStringDate(fecha_recibida: String, format_original: String, format: String): String {
//        var fecha = ""
//        try {
//            val date = SimpleDateFormat(format_original, Locale("es", "ES")).parse(fecha_recibida)
//            fecha = SimpleDateFormat(format, Locale("es", "ES")).format(date)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//
//        return fecha
//    }

    fun abrirNavegador(activity: Activity, url: String) {
        try {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            activity.startActivity(i)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}