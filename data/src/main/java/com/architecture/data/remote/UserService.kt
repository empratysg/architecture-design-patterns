package com.architecture.data.remote

import com.architecture.data.model.UserEntity
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface UserService {

    @GET("profile")
    fun getProfile(): Flowable<List<UserEntity>>
}