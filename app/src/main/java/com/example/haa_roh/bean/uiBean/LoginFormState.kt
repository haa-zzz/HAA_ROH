package com.example.haa_roh.bean.uiBean

/**
 * author: haa-zzz
 * time: 2021-7-27
 * LiveData中用来判断登录状态的bean
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false)