package com.example.haa_roh.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    /**
     * Toasty：显示错误Toast
     */
    fun showErrorToast(
        context: Context,
        msg: String,
        withIcon: Boolean = true,
        duration: Int = Toast.LENGTH_SHORT
    ) {
        // Toasty.error(context, msg, duration, withIcon).show()
        Toast.makeText(context, msg, duration).show()
    }

    /**
     * Toasty：显示成功Toast
     */
    fun showSuccessToast(
        context: Context,
        msg: String,
        withIcon: Boolean = true,
        duration: Int = Toast.LENGTH_SHORT
    ) {
        // Toasty.success(context, msg, duration, withIcon).show()
        Toast.makeText(context, msg, duration).show()
    }
}