package com.example.architecturedesignpattern.ui

import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.GetAvailableUsersUseCase
import com.example.domain.usecases.GetCurrentUserUseCase
import com.example.domain.usecases.RemoveUserUseCase
import com.example.domain.usecases.SaveUserUseCase
import io.reactivex.rxjava3.core.Flowable

class MainModel(
    private val getAvailableUsersUseCase: GetAvailableUsersUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val removeUserUseCase: RemoveUserUseCase
) {

    fun getAvailableUsers(): Flowable<List<User>> {
        return getAvailableUsersUseCase.getAvailableUsers()
    }

    fun saveUser(userName: String, password: String) {
        saveUserUseCase.saveUser(User(userName, password))
    }

    fun getCurrentUser(): Flowable<User> {
        return getCurrentUserUseCase.getCurrentUser()
    }

    fun removeUser() {
        removeUserUseCase.removeUser()
    }
}