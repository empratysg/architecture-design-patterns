package com.example.architecturedesignpattern.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.architecturedesignpattern.R
import com.example.architecturedesignpattern.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.btnSignOut.setOnClickListener {
            mainViewModel.logout()
        }
        binding.btnSingIn.setOnClickListener {
            mainViewModel.login(
                binding.edtUserName.text.toString(),
                binding.edtPassword.text.toString()
            )
        }
        mainViewModel.checkUserLogin()

    }

    override fun onStart() {
        super.onStart()
        mainViewModel.isUserLoggedIn.observe(this) {
            if (it) {
                binding.tvWelcome.visibility = View.VISIBLE
                binding.btnSignOut.visibility = View.VISIBLE

                binding.edtUserName.visibility = View.INVISIBLE
                binding.edtPassword.visibility = View.INVISIBLE
                binding.btnSingIn.visibility = View.INVISIBLE
            } else {
                binding.tvWelcome.visibility = View.INVISIBLE
                binding.btnSignOut.visibility = View.INVISIBLE

                binding.edtUserName.visibility = View.VISIBLE
                binding.edtPassword.visibility = View.VISIBLE
                binding.btnSingIn.visibility = View.VISIBLE
            }
        }

        mainViewModel.dataUser.observe(this) {
            edt_user_name.setText("")
            edt_password.setText("")
            binding.tvWelcome.text = it?.userName ?: ""
        }

        mainViewModel.errorMessage.observe(this){
            if(it.isNotBlank()){
                Toast.makeText(this, it,Toast.LENGTH_LONG).show()
            }
        }
    }
}