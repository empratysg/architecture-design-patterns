package com.architecture.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.domain.models.User
import io.reactivex.rxjava3.core.Flowable

class UserSharedPref(context: Context) : UserDataStore {
    companion object {
        private const val USER_NAME = "user_name";
        private const val PASSWORD = "password";
    }

    private val prefs =
        context.getSharedPreferences("architecture.design.pattern", Context.MODE_PRIVATE)

    override fun getUser(): Flowable<User> {
        val userName = prefs.getString(USER_NAME, null);
        val password = prefs.getString(PASSWORD, null);
        if (userName != null && password != null) {
            return Flowable.just(User(userName, password))
        }
        return Flowable.error(NullPointerException())
    }

    override fun saveUser(userName: String, password: String) {
        prefs.edit { putString(USER_NAME, userName) }
        prefs.edit { putString(PASSWORD, password) }
    }

    override fun removeUser() {
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