package com.example.domain.repositories

import com.example.domain.models.User

interface UserRepository {
    fun getCurrentUser(): User?
    fun saveUser(user: User)
    fun removeUser()
}