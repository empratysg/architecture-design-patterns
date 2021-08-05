package com.example.architecturedesignpattern.ui

import androidx.lifecycle.MutableLiveData
import com.example.architecturedesignpattern.common.BaseViewModel
import com.example.domain.models.User


class MainViewModel(private val mainModel: MainModel) : BaseViewModel() {

    val isUserLoggedIn = MutableLiveData<Boolean>()
    val dataUser = MutableLiveData<User?>()
    val errorMessage = MutableLiveData<String>()

    fun checkUserLogin() {
        val disposable = mainModel.getCurrentUser().subscribe(
            {
                isUserLoggedIn.value = true
                dataUser.postValue(it)
            },
            {
                if (it is NullPointerException) {
                    isUserLoggedIn.postValue(false)
                }
            }
        )
        addDisposable(disposable)
    }

    fun logout() {
        mainModel.removeUser()
        dataUser.value = null
        isUserLoggedIn.postValue(false)
    }

    fun login(userName: String, password: String) {

        if (userName.isNotBlank() && password.isNotBlank()) {
            mainModel.getAvailableUsers().subscribe {
                for (u in it) {
                    if (userName == u.userName && password == u.password) {
                        dataUser.value = u
                        isUserLoggedIn.postValue(true)
                        break
                    }
                }
                if (dataUser.value == null) {
                    isUserLoggedIn.postValue(false)
                    errorMessage.postValue("Login Failed")

                }
            }

        } else {
            errorMessage.postValue("Invalid Data")
        }
    }
}