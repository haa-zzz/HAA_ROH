package com.example.haa_roh.ui.my.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haa_roh.R

class My_Edit_Fragment : Fragment() {

    companion object {
        fun newInstance() = My_Edit_Fragment()
    }

    private lateinit var viewModel: MyEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my__edit__fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}