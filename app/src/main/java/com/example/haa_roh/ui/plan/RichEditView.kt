package com.example.haa_roh.ui.plan

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.haa_roh.R
import com.example.haa_roh.base.BaseFragment
import com.example.haa_roh.bean.Bmob.PlanAdapterBean
import com.example.haa_roh.bean.room.PlanRoom
import com.example.haa_roh.databinding.RichEditBinding
import com.example.haa_roh.db.PHONEMES
import com.example.haa_roh.db.querySpNumber
import com.example.haa_roh.db.room.changePlanToRoom
import com.example.haa_roh.util.printLog
import com.haazzz.haabase.resolver.ImageGetter
import com.haazzz.haabase.resolver.MyTagHandler


class RichEditView : BaseFragment() {

    private lateinit var binding: RichEditBinding
    private var title: String? = null
    private var tags: String? = null
    private var id: String? = null
    private var content: String? = null

    private lateinit var viewModel: RichEditViewViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.rich_edit, container, false)
        binding = RichEditBinding.bind(view)
        initData()
        initView()

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initData() {
        viewModel = ViewModelProvider(this).get(RichEditViewViewModel::class.java)
        val bundle = arguments
        if (bundle != null) {
            title = bundle.getString("title")
            tags = bundle.getString("tag")
            content =  bundle.getString("content")
            id = bundle.getString("id")
        }
        viewModel.changedPlanLiveData.observe(requireActivity(), {
            val result = it ?: return@observe
            if(result.error == null){
                showSuccessToast(requireContext(),"修改成功")
                val planRoom = PlanRoom(id = id!!,number = PHONEMES?:querySpNumber(),
                    title = title,tag = tags,isFinish = true,content = binding.EditViewMiddle.RichEditEditView?.createHtml())
                changePlanToRoom(planRoom)
            }else {
                showErrorToast(requireContext(),"修改失败")
            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun initView() {

        binding.RichEditChoiceMenu.bindingEditView(binding.EditViewMiddle.RichEditEditView!!)

        binding.EditViewMiddle.RichEditTitle.text = title

        if (content != null) {
            printLog(content!!)
            val spanned = Html.fromHtml(content,
                Html.FROM_HTML_MODE_LEGACY, ImageGetter(requireContext(), binding.EditViewMiddle.RichEditEditView!!), MyTagHandler()
            )
            binding.EditViewMiddle.RichEditEditView!!.text = spanned as Editable?

        }

        binding.RichEditToolbar.RichEditReturnButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.RichEditToolbar.RichEditSureButton.setOnClickListener {
            viewModel.changePlan(
                id!!,
                PlanAdapterBean(content = binding.EditViewMiddle.RichEditEditView?.createHtml())
            )
        }

    }
    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener(View.OnKeyListener { _, i, keyEvent ->
            if (keyEvent.action === KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK) {
                findNavController().popBackStack()
                return@OnKeyListener true
            }
            false
        })
    }


}