package com.architecture.data.tranforms

import com.architecture.data.model.UserEntity
import com.example.domain.models.User

fun UserEntity.toUser() = User(userName, password)

fun List<UserEntity>.toListUser() = map { it.toUser() }