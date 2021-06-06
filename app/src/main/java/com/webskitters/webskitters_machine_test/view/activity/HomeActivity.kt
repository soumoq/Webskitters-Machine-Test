package com.webskitters.webskitters_machine_test.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.webskitters.webskitters_machine_test.R
import com.webskitters.webskitters_machine_test.util.toast
import com.webskitters.webskitters_machine_test.viewModel.ImageViewModel

class HomeActivity : AppCompatActivity() {

    var imageViewModel : ImageViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Implement bottom nav
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        //NavigationUI.setupActionBarWithNavController(this, navController)

        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)
        imageViewModel?.getImage(this)

    }

    @JvmName("getImageViewModel1")
    fun getImageViewModel(): ImageViewModel? {
        return imageViewModel
    }


}