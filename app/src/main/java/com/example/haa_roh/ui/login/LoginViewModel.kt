package com.example.haa_roh.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haa_roh.R
import com.example.haa_roh.base.BaseViewModel
import com.example.haa_roh.bean.uiBean.*
import com.example.haa_roh.repository.login.LoginRepository
import com.example.haa_roh.util.isValidPhoneNumber

class LoginViewModel : BaseViewModel() {

    //使ViewModel只对观察者暴露不可修改的LiveData对象
    private val loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = loginForm

    private val _loginGetAutoCode = MutableLiveData<LoginAutoCode>()
    val loginGetAutoCode : LiveData<LoginAutoCode> = _loginGetAutoCode

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    /*
    (private val loginRepository: LoginRepository)
    //向外部暴漏的login方法,执行点击登录按钮后的操作，并将结果通过LiveData传递到Activity
    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

     */

    //向外部 暴露 的loginDataChanged方法,当登录状态发生改变后 判断用户名或密码是否合法
    fun loginDataChanged(username: String, password: String) {
        if (!isValidPhoneNumber(username)) {
            loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else {
            loginForm.value = LoginFormState(isDataValid = true)
        }
    }
    //
}