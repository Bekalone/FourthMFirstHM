package com.example.fourthmfirsthm

import android.content.Context

class Prefs(context: Context) {

    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveState() {
        preferences.edit().putBoolean("isShow", true).apply()
    }

    fun isShown(): Boolean {
        return preferences.getBoolean("isShow", false)
    }

    fun saveText(text: String){
        preferences.edit().putString("getText",text).commit()
    }

    fun getText(): String? {
        return preferences.getString("getText", "")
    }
}