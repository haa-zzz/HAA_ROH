package com.example.haa_roh.repository.login

import com.example.haa_roh.bean.uiBean.LoggedInUser
import com.example.haa_roh.bean.uiBean.Result
import java.io.IOException
import java.util.*

class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }
    fun logout() {
        // TODO: revoke authentication
    }
}