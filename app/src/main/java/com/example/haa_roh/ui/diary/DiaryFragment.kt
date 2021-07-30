package com.example.haa_roh.ui.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.haa_roh.base.BaseFragment
import com.example.haa_roh.databinding.FragmentDashboardBinding

class DiaryFragment : BaseFragment() {

    private lateinit var planViewModel: DiaryViewModel
    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        planViewModel = ViewModelProvider(this).get(DiaryViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textDashboard
        planViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}