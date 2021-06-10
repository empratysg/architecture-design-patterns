package com.architecture.data.model

import com.example.domain.models.User

data class UserEntity(var userName: String, var password: String)

fun UserEntity.toUser() = User(userName, password)