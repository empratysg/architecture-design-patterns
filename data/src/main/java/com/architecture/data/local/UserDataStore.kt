package com.architecture.data.local

import com.example.domain.models.User
import io.reactivex.rxjava3.core.Flowable

interface UserDataStore {
    fun getUser(): Flowable<User>
    fun saveUser(userName: String, password: String)
    fun removeUser()
}