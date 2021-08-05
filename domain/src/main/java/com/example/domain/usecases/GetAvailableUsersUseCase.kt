package com.example.domain.usecases

import com.example.domain.common.BaseFlowableUseCase
import com.example.domain.common.FlowableRxTransformer
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import io.reactivex.rxjava3.core.Flowable

class GetAvailableUsersUseCase(
    private val transformer: FlowableRxTransformer<List<User>>,
    private val repository: UserRepository
) : BaseFlowableUseCase<List<User>>(transformer) {

    fun getAvailableUsers() = single()

    override fun createFlowable(data: Map<String, Any>?): Flowable<List<User>> {
        return repository.getAvailableUsers()
    }
}