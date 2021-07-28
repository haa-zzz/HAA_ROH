package com.example.haa_roh.bean.uiBean

import com.example.haa_roh.bean.uiBean.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)