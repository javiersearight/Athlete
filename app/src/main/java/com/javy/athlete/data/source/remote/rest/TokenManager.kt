package com.javy.athlete.data.source.remote.rest

import android.content.Context

class TokenManager(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("authentication_prefs", Context.MODE_PRIVATE)

    private val TOKEN_KEY = "token"

    fun persistToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String? =
        sharedPreferences.getString(TOKEN_KEY, "")
}