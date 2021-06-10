package com.example.domain.usecases

import com.example.domain.repositories.UserRepository

class RemoveUserUseCase(private val repository: UserRepository) {
    fun removeUser() = repository.removeUser()
}