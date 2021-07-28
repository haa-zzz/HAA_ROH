package com.example.haa_roh.util

import java.util.regex.Pattern

/**
匹配是否是手机号
 */
fun isValidPhoneNumber(phoneNumber: String?): Boolean {
    return if (phoneNumber != null && phoneNumber.isNotEmpty()) {
        Pattern.matches("^1[3-9]\\d{9}$", phoneNumber)
    } else false
}
