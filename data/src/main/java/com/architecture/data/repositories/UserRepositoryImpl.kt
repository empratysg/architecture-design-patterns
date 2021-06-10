package com.architecture.data.repositories

import com.architecture.data.local.UserSharedPref
import com.architecture.data.model.toUser
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository

class UserRepositoryImpl(private val userSharedPref: UserSharedPref) : UserRepository {
    override fun getCurrentUser(): User? {
        return userSharedPref.getUser()?.toUser()
    }

    override fun saveUser(user: User) {
        userSharedPref.saveUser(user.userName, user.password)
    }

    override fun removeUser() {
        userSharedPref.removeUser()
    }
}