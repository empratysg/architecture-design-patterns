package com.example.domain.usecases

import com.example.domain.common.BaseFlowableUseCase
import com.example.domain.common.FlowableRxTransformer
import com.example.domain.models.User
import com.example.domain.repositories.UserRepository
import io.reactivex.rxjava3.core.Flowable

class GetCurrentUserUseCase(
    private val transformer: FlowableRxTransformer<User>,
    private val repository: UserRepository):BaseFlowableUseCase<User>(transformer)
{
    fun getCurrentUser() = single()
    override fun createFlowable(data: Map<String, Any>?): Flowable<User> {
        return repository.getCurrentUser()
    }
}