package com.example.architecturedesignpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.architecture.data.local.UserSharedPref
import com.architecture.data.repositories.UserRepositoryImpl
import com.example.domain.models.User

class MainActivity : AppCompatActivity() {
    private lateinit var mainModel: MainModel
    private lateinit var userNameEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var signInBtn: Button
    private lateinit var signInLayout: LinearLayout
    private lateinit var welcomeTv: TextView
    private lateinit var signOutBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainModel = MainModel(UserRepositoryImpl(UserSharedPref(applicationContext)))

        userNameEdt = findViewById(R.id.edt_user_name)
        passwordEdt = findViewById(R.id.edt_password)
        signInBtn = findViewById(R.id.btn_sing_in)
        signInLayout = findViewById(R.id.ll_sign_in)
        welcomeTv = findViewById(R.id.tv_welcome)
        signOutBtn = findViewById(R.id.btn_sign_out)

        signInBtn.setOnClickListener {
            val userName: String = userNameEdt.text.toString()
            val password: String = passwordEdt.text.toString()
            if (userName.isNotBlank() && password.isNotBlank()) {
                mainModel.saveUser(userName, password)
                welcomeTv.text = "Welcome $userName"
                signInLayout.visibility = View.GONE
                welcomeTv.visibility = View.VISIBLE
                signOutBtn.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }

        signOutBtn.setOnClickListener {
            mainModel.removeUser()
            userNameEdt.setText("")
            passwordEdt.setText("")
            signInLayout.visibility = View.VISIBLE
            welcomeTv.visibility = View.INVISIBLE
            signOutBtn.visibility = View.INVISIBLE
        }
        val user: User? = mainModel.getCurrentUser()
        if (user != null) {
            welcomeTv.text = "Welcome ${user.userName}"
            signInLayout.visibility = View.GONE
            welcomeTv.visibility = View.VISIBLE
            signOutBtn.visibility = View.VISIBLE
        }
    }
}