package tech.dobrobot.apps.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import tech.dobrobot.apps.R

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), NavigationHost {

    companion object {
        private val TOP_LEVEL_DESTINATION = setOf(
            R.id.navigation_translate,
            R.id.navigation_history
        )
    }

    private lateinit var appConfiguration: AppBarConfiguration

    private var navController: NavController? = null
    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as? NavHostFragment ?: return
        navController = findNavController(R.id.mainNavHostFragment)
        appConfiguration = AppBarConfiguration(TOP_LEVEL_DESTINATION)

        navController?.apply {
            mainBottomNavView.setupWithNavController(this)
        }
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        if (navController != null)
            toolbar.setupWithNavController(navController!!, appConfiguration)
    }
}