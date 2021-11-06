package com.example.haa_roh.ui.plan

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.haa_roh.R
import com.example.haa_roh.api.RecyclerViewCallBack
import com.example.haa_roh.api.SlideRecyclerView
import com.example.haa_roh.base.BaseFragment
import com.example.haa_roh.bean.PlanDataBingBean
import com.example.haa_roh.bean.room.PlanRoom
import com.example.haa_roh.databinding.FragmentPlanBinding
import com.example.haa_roh.databinding.ItemAddplanBinding
import com.example.haa_roh.ui.adapter.PlanAdapter
import com.example.haa_roh.util.dialogBtw
import com.example.haa_roh.util.printLog
import com.haazzz.haabase.util.TAG
import java.util.*
import kotlin.collections.ArrayList

class PlanFragment : BaseFragment() {

    private lateinit var viewModel: PlanModel
    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var bean: PlanDataBingBean
    private var dialog: Dialog? = null
    private lateinit var addPlanBinding: ItemAddplanBinding
    private lateinit var adapter : PlanAdapter
    private lateinit var list : MutableList<PlanRoom>
    private lateinit var recyclerView : SlideRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(PlanModel::class.java)
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        initView()
        initData()
        return binding.root
    }

    private fun initView() {
        initRecyclerView()
        binding.planFloatAction.setOnClickListener { initAddPlan() }
    }

    private fun initData() {
        bean = PlanDataBingBean()

        viewModel.addPlanLiveData.observe(requireActivity(), {
            val result = it ?: return@observe
            if(result.error != null){
                showErrorToast(requireContext(),"创建失败，请检查网络")
            }
        })
        viewModel.queryPlan()
        viewModel.getQueryPlanLiveData().observe(requireActivity(),{
            it ?: return@observe
            if(it.isEmpty()){
                viewModel.queryPlanFromBMob()
            }
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

        viewModel.deletePlanLiveData.observe(requireActivity(),{
            val result = it ?: return@observe
            if(result.error != null){
                showSuccessToast(requireContext(),"删除成功")
            }else{
                showErrorToast(requireContext(),"删除失败")
            }
        })
    }
    private fun initRecyclerView() {

        list = arrayListOf()
        adapter = PlanAdapter(list = list as ArrayList<PlanRoom>,object : RecyclerViewCallBack{

            override fun callBack(index: Int) {
                val bundle = Bundle()
                bundle.putString("title",list[index].title)
                bundle.putString("tag",list[index].tag)
                bundle.putString("content", list[index].content)
                bundle.putString("id",list[index].id)
                Navigation.findNavController(view!!).navigate(R.id.action_navigation_plan_to_richEditView,bundle)
            }

            override fun deleteClickBack(index: Int, id: String) {
                list.removeAt(index)
                recyclerView.closeMenu()
                adapter.notifyDataSetChanged()
                viewModel.deletePlan(id)

            }
            override fun sureClickBack(index: Int, id: String) {
                val planRoom = list[index]
                list.removeAt(index)
                list.add(planRoom)
                recyclerView.closeMenu()
                adapter.notifyDataSetChanged()
            }
        })
        recyclerView =  binding.planToBeCompletedRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

//        binding.planToBeCompletedRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.planToBeCompletedRecyclerView.adapter = adapter
       // binding.planToBeCompletedRecyclerView.addI
    }
    /*
    以Dialog展示，并绑定DataBinding，处理点击事件等
     */
    private fun initAddPlan() {
        val addPlanBtw: View = View.inflate(context, R.layout.item_addplan, null)
        addPlanBinding = ItemAddplanBinding.bind(addPlanBtw)
        addPlanBinding.planBean = bean
        //防止dialog被多次创建
        if (dialog == null) {
            dialog = dialogBtw(addPlanBtw, requireContext())
        } else {
            dialog?.show()
        }
        initSpinner()

        addPlanBinding.planCreateAffirm.setOnClickListener {
            if (bean.editText.get() == null) {
                showErrorToast(requireContext(), "计划不能为空")
            } else {
                //viewModel.addToBeCompleted(bean.editText.get()!!, bean.tag.get()!!)
                viewModel.addToBeCompleted(bean.editText.get()!!, bean.tag.get()!!)
                dialog?.dismiss()
            }
        }
    }
    private fun initSpinner() {
        val spinner = addPlanBinding.spinner
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item, viewModel.getAllTag()
        )
        adapter.setDropDownViewResource(R.layout.spinner_select)
        spinner.adapter = adapter
        //给下拉框添加监听事件
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
               bean.tag.set(viewModel.getAllTag()[i])
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}