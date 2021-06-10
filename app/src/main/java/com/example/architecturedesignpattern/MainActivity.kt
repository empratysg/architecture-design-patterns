package com.example.architecturedesignpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.architecture.data.local.UserSharedPref
import com.architecture.data.repositories.UserRepositoryImpl
import com.example.architecturedesignpattern.databinding.ActivityMainBinding
import com.example.domain.models.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainModel = MainModel(UserRepositoryImpl(UserSharedPref(applicationContext)))

        val viewModel = MainViewModel(mainModel)
        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityMainBinding.viewModel = viewModel
        activityMainBinding.executePendingBindings()

        viewModel.checkUserLogin()
    }
}