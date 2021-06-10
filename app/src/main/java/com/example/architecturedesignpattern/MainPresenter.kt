package com.example.architecturedesignpattern

import android.view.View
import android.widget.Toast
import com.example.domain.models.User

class MainPresenter(private val view: MainContract.View, private val model: MainModel) :
    MainContract.Presenter {
    override fun login(userName: String, password: String) {
        if (userName.isNotBlank() && password.isNotBlank()) {
            model.saveUser(userName, password)
            view.doWelcome("Welcome $userName")
            view.hideSignInView()
        } else {
            view.showError("Invalid input")
        }
    }

    override fun checkLogin() {
        val user: User? = model.getCurrentUser()
        if (user != null) {
            view.doWelcome("Welcome ${user.userName}")
            view.hideSignInView()
        } else {
            view.hideSignInView()
        }
    }

    override fun logOut() {
        view.clearInput()
        view.showSignInView()
    }
}