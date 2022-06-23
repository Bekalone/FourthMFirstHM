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


}