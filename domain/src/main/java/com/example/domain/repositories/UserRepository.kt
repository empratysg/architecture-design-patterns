package com.example.domain.repositories

import com.example.domain.models.User
import io.reactivex.rxjava3.core.Flowable

interface UserRepository {
    fun getAvailableUsers(): Flowable<List<User>>
    fun getCurrentUser(): Flowable<User>
    fun saveUser(user: User)
    fun removeUser()
}