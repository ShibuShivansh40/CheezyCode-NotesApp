package com.example.cheezycode_notesapp.utils

import android.content.Context
import com.example.cheezycode_notesapp.utils.Constants.PREFS_TOKEN_FILE
import com.example.cheezycode_notesapp.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


//This is used for Shared Preference to make the App's Content persistent
class TokenManager @Inject constructor(@ApplicationContext context : Context) {

    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)

    fun saveToken(token : String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN , token)
        editor.apply()
    }

    fun getToken(): String?{
        return prefs.getString(USER_TOKEN, null)
    }
}