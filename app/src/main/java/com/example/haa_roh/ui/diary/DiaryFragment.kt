package com.example.haa_roh.ui.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.haa_roh.base.BaseFragment
import com.example.haa_roh.bean.DailyData
import com.example.haa_roh.databinding.FragmentDiaryBinding
import com.example.haa_roh.repository.retrofitPostImage
import com.example.haa_roh.util.printLog

class DiaryFragment : BaseFragment() {

    private lateinit var diaryViewModel: DiaryViewModel
    private lateinit var binding : FragmentDiaryBinding
    private lateinit var diaryData : DailyData

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        diaryViewModel = ViewModelProvider(this).get(DiaryViewModel::class.java)
        binding = FragmentDiaryBinding.inflate(inflater, container, false)

        val root: View = binding.root

        initData()
        initView()

        return root
    }

    private fun initView() {



    }

    private fun initData() {
        diaryData = DailyData()
        binding.data = diaryData
        diaryViewModel.getAllData()

        diaryViewModel.diaryLiveData.observe(requireActivity()) {
            val result = it ?: return@observe
            if (result.photo.get() != null) {
                printLog("收到： ${result.photo.get()}")
                diaryData.photo.set(result.photo.get())
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}