package dev.tuanteo.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import dev.tuanteo.note.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /*TuanTeo: Thêm Back Button trên AppBar cho NavController */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.note_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    /*TuanTeo: Sự kiện Back Button trên AppBar cho NavController */
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.note_nav_host_fragment)
        return navController.navigateUp()
    }
}