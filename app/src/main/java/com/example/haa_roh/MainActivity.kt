package com.example.haa_roh

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.*
import com.example.haa_roh.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        destinationChangedListener()
    }

    private fun destinationChangedListener() {
        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {

            }

        })
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
    private fun initDrawer() {
        val navigationView = binding.navigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }
    private fun initBNV() {
        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}