package com.example.haa_roh.bean.uiBean

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: Boolean = false,
    val error: String? = null
)