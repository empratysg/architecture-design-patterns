package com.example.architecturedesignpattern.di

import com.architecture.data.local.UserDataStore
import com.architecture.data.local.UserSharedPref
import com.architecture.data.remote.UserService
import com.architecture.data.repositories.UserRepositoryImpl
import com.example.architecturedesignpattern.common.AsyncFlowableTransformer
import com.example.architecturedesignpattern.ui.MainModel
import com.example.architecturedesignpattern.ui.MainViewModel
import com.example.architecturedesignpattern.utils.*
import com.example.domain.repositories.UserRepository
import com.example.domain.usecases.GetAvailableUsersUseCase
import com.example.domain.usecases.GetCurrentUserUseCase
import com.example.domain.usecases.RemoveUserUseCase
import com.example.domain.usecases.SaveUserUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModules = module {
    single<UserService>(StringQualifier(REMOTE)) { get(StringQualifier(API)) }
    single<UserDataStore>(StringQualifier(LOCAL)) {
        UserSharedPref(androidContext())
    }
    single<UserRepository> {
        UserRepositoryImpl(
            remote = get(StringQualifier(REMOTE)),
            local = get(StringQualifier(LOCAL))
        )
    }
}

val useCaseModules = module {
    factory(StringQualifier(GET_AVAILABLE_USERS_USE_CASE)) {
        GetAvailableUsersUseCase(
            transformer = AsyncFlowableTransformer(),
            repository = get()
        )
    }
    factory(StringQualifier(GET_CURRENT_USER_USE_CASE)) {
        GetCurrentUserUseCase(
            transformer = AsyncFlowableTransformer(),
            repository = get()
        )
    }
    factory(StringQualifier(SAVE_USER_USE_CASE)) { SaveUserUseCase(repository = get()) }
    factory(StringQualifier(REMOVE_USER_USE_CASE)) { RemoveUserUseCase(repository = get()) }
}

val networkModules = module {
    single(StringQualifier(RETROFIT_INSTANCE)) { createNetworkClient(BASE_URL) }
    single<UserService>(StringQualifier(API)) {
        (get(StringQualifier(RETROFIT_INSTANCE)) as Retrofit).create(
            UserService::class.java
        )
    }
}

val localModules = module {
    single<UserDataStore>(StringQualifier(SHARED_PREFERENCE)) { UserSharedPref(androidContext()) }
}

val viewModels = module {
    viewModel {
        MainViewModel(get(StringQualifier(MAIN_MODEL)))
    }
}

val modelModules = module {
    factory(StringQualifier(MAIN_MODEL)) {
        MainModel(
            get(StringQualifier(GET_AVAILABLE_USERS_USE_CASE)),
            get(StringQualifier(GET_CURRENT_USER_USE_CASE)),
            get(StringQualifier(SAVE_USER_USE_CASE)),
            get(StringQualifier(REMOVE_USER_USE_CASE))
        )
    }
}

