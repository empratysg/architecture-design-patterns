package com.architecture.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.architecture.data.model.UserEntity

class UserSharedPref(context: Context) {
    companion object {
        private const val USER_NAME = "user_name";
        private const val PASSWORD = "password";
    }

    private val prefs =
        context.getSharedPreferences("architecture.design.pattern", Context.MODE_PRIVATE)

    fun getUser(): UserEntity? {
        val userName = prefs.getString(USER_NAME, null);
        val password = prefs.getString(PASSWORD, null);
        if (userName != null && password != null) {
            return UserEntity(userName, password)
        }
        return null
    }

    fun saveUser(userName: String, password: String) {
        prefs.edit { putString(USER_NAME, userName) }
        prefs.edit { putString(PASSWORD, password) }
    }

    fun removeUser(){
        prefs.edit().clear().apply()
    }
}

@SuppressLint("ApplySharedPref")
public inline fun SharedPreferences.edit(
    commit: Boolean = false,
    action: SharedPreferences.Editor.() -> Unit
) {
    val editor = edit()
    action(editor)
    if (commit) {
        editor.commit()
    } else {
        editor.apply()
    }
}