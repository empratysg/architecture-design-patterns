package com.example.architecturedesignpattern

import com.example.domain.models.User
import com.example.domain.repositories.UserRepository

class MainModel(private val userRepository: UserRepository) {

    fun saveUser(userName: String, password: String) {
        userRepository.saveUser(User(userName, password))
    }

    fun getCurrentUser(): User? {
        return userRepository.getCurrentUser()
    }

    fun removeUser() {
        userRepository.removeUser()
    }
}