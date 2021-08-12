package com.example.haa_roh.ui.plan

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import com.example.haa_roh.R
import com.example.haa_roh.databinding.RichEditBinding

class RichEditView : Fragment() {

    private lateinit var binding : RichEditBinding
    private var title : String? = null
    private var tags : String? = null
    private var content : String? = null
    companion object {
        fun newInstance() = RichEditView()
    }

    private lateinit var viewModel: RichEditViewViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = arguments
        if(bundle!=null){
            title = bundle.getString("title")
            tags = bundle.getString("age")
            content = bundle.getString("content")
        }

        val view =  inflater.inflate(R.layout.rich_edit, container, false)
        binding = RichEditBinding.bind(view)

        initView()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView() {
        binding.RichEditChoiceMenu.bindingEditView(binding.EditViewMiddle.RichEditEditView!!)
        binding.EditViewMiddle.RichEditTitle.text = title

//            binding.RichEditToolbar.RichEditReturnButton.setOnClickListener{
//
//
//            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RichEditViewViewModel::class.java)

    }

}