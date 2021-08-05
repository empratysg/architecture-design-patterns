package com.architecture.data.repositories

import com.architecture.data.local.UserDataStore
import com.architecture.data.remote.UserService
import com.architecture.data.tranforms.toListUser
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import io.reactivex.rxjava3.core.Flowable

class UserRepositoryImpl(
    private val remote: UserService,
    private val local: UserDataStore
) : UserRepository {

    override fun getAvailableUsers(): Flowable<List<User>> {
        return remote.getProfile().map { list -> list.toListUser() }
    }

    override fun getCurrentUser(): Flowable<User> {
        return local.getUser()
    }

    override fun saveUser(user: User) {
        local.saveUser(user.userName, user.password)
    }

    override fun removeUser() {
        local.removeUser()
    }
}