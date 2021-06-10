package com.example.architecturedesignpattern

interface MainContract {
    interface View {
        fun doWelcome(welcome: String)
        fun clearInput()
        fun showSignInView()
        fun hideSignInView()
        fun showError(error: String)
    }

    interface Presenter {
        fun login(userName: String, password: String)
        fun logOut()
        fun checkLogin()
    }
}