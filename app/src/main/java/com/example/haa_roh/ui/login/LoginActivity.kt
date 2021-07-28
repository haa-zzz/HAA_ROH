package com.example.haa_roh.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.haa_roh.base.BaseActivity
import com.example.haa_roh.databinding.ActivityLoginBinding
import com.example.haa_roh.util.afterTextChanged

class LoginActivity : BaseActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()

    }
    private fun initView() {
        //获取ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun initData() {

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //获取输入并检测输入格式是否正确
        initInputData()
        initAuthCodeData()
    }

    private fun initAuthCodeData() {

    }
    private fun initInputData() {
        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading
        val authCode = binding.authCode

        //输入发生改变后，调用ViewModel中的方法区判断输入是否有异常
        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }
        password.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }
        loginViewModel.loginFormState.observe(this, Observer {
            val loginState = it ?:  return@Observer
            //根据登录状态 设置 login按钮是否可以点击
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        authCode.setOnClickListener{

        }
        login.setOnClickListener{
            //点击登录后设置 等待 可见
            loading.visibility = View.VISIBLE
            //
        }
    }



}