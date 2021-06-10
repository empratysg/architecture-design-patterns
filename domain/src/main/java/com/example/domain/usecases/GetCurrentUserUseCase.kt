package com.example.domain.usecases

import com.example.domain.repositories.UserRepository

class GetCurrentUserUseCase(private val repository: UserRepository) {
    fun getCurrentUser() = repository.getCurrentUser()
}