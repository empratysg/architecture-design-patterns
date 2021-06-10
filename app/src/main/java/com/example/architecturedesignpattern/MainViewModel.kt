package com.example.architecturedesignpattern

import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter


class MainViewModel(private val mainModel: MainModel) : BaseObservable() {

    @get:Bindable
    var userLoggedIn: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.userLoggedIn)
        }

    @get:Bindable
    var userName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.userName)
        }

    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    var toastMessage: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.toastMessage)
        }

    fun checkUserLogin() {
        val user = mainModel.getCurrentUser()
        if (user != null) {
            userLoggedIn = true
            userName = user.userName
        } else {
            userLoggedIn = false
        }
    }

    fun login() {
        if (userName.isNotBlank() && password.isNotBlank()) {
            mainModel.saveUser(userName, password)
            userLoggedIn = true
        }else{
            toastMessage = "Invalid input"
        }
    }

    fun logout(){
        mainModel.removeUser()
        userName = ""
        password = ""
        userLoggedIn = false
    }
}

@BindingAdapter("toastMessage")
fun runMe(view: View, message: String?) {
    if (message != null) Toast.makeText(view.context, message, Toast.LENGTH_SHORT).show()
}