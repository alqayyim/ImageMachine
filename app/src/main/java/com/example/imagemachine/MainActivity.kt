package com.example.imagemachine

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.core.visible
import com.example.imagemachine.databinding.ActivityMainBinding
import com.example.imagemachine.utils.delegate.viewBinding
import com.permissionx.guolindev.PermissionX

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var navHostFragment: NavHostFragment
    private val navController by lazy { navHostFragment.navController }

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        when (destination.id) {
            R.id.nav_machine_list,
            R.id.nav_qr -> binding.navView.visible(true)

            else -> binding.navView.visible(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.nav_machine_list, R.id.nav_qr))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        askPermission()
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun askPermission() {
        PermissionX.init(this)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .request { allGranted, _, deniedList ->
                if (allGranted) {

                }
            }
    }
}