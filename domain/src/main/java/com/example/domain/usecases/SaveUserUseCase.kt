package com.example.domain.usecases

import com.example.domain.models.User
import com.example.domain.repositories.UserRepository

class SaveUserUseCase(private val repository: UserRepository) {
    fun saveUser(user: User) = repository.saveUser(user)
}