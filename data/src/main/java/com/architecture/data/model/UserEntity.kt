package com.architecture.data.model

import com.example.domain.models.User
import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("user_name")
    var userName: String,
    @SerializedName("password")
    var password: String)