package com.example.haa_roh

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.haa_roh.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: BottomNavigationView

    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ifToLogin()
        initView()
        initData()
        destinationChangedListener()
    }

    private fun ifToLogin() {

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
        //mainViewModel.getPIFromDb()
    }
    private fun initDrawer() {
        val navigationView = binding.navigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_plan
            ), drawerLayout
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }

    private fun initBNV() {
        navView = binding.navView
        navView.setupWithNavController(navController)
    }

    private fun destinationChangedListener() {
        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                when(destination.id){
                    //如果是做左边菜单，设置底部导航栏不可见
                    R.id.navigation_advice,R.id.navigation_about,R.id.navigation_settings ->{
                        navView.visibility = View.GONE
                    }
                    else -> {
                        navView.visibility = View.VISIBLE
                    }
                }
            }

        })
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}