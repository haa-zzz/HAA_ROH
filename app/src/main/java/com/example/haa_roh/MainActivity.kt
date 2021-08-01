package com.example.haa_roh

import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.haa_roh.base.BaseActivity

import com.example.haa_roh.databinding.ActivityMainBinding
import com.example.haa_roh.databinding.MyHeadBinding


import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: BottomNavigationView

    private lateinit var mainViewModel: MainViewModel

    private lateinit var headBinding: MyHeadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        destinationChangedListener()
    }

    private fun initView() {
        //初始化ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //设置toolbar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        //拿到navController
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
        initDrawer()
        initBNV()
    }

    private fun initData() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //headLayout绑定数据
        headBinding = MyHeadBinding.bind(binding.navigationView.getHeaderView(0))
        headBinding.apply {
            lifecycleOwner = this@MainActivity
        }
        mainViewModel.getPIFromRoom()

        mainViewModel.getInitInformation().observe(this, {
            if (it == null) {
                showErrorToast(this, "监测到数据库为空")
                mainViewModel.getPIFromBMob()
            }else{
                headBinding.apply {
                    photo = it.photo
                    username = it.username
                    status = it.state
                }
            }
        })
        mainViewModel.bMobResult.observe(this, {
            val result = it ?: return@observe
            if (result.success) {
                mainViewModel.addPIToRoom(result.personalInformation)
            } else {
                showErrorToast(this, "获取数据失败，请检查网络连接")
            }
        })
    }

    private fun initDrawer() {
        val navigationView = binding.navigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_plan
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }

    private fun initBNV() {
        navView = binding.navView
        navView.setupWithNavController(navController)
    }

    private fun destinationChangedListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                //如果是做左边菜单，设置底部导航栏不可见
                R.id.navigation_advice, R.id.navigation_about, R.id.navigation_settings -> {
                    navView.visibility = View.GONE
                }
                else -> {
                    navView.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}