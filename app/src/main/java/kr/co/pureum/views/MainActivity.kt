package kr.co.pureum.views

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initView() {
        initToolBar();
        initBottomNavigation();
    }

    private fun initToolBar() {
        val toolbarBodyTemplate = binding.mainToolbar
        setSupportActionBar(toolbarBodyTemplate)
        supportActionBar?.title = ""
    }

    private var navController : NavController? = null
    private fun initBottomNavigation() {
        navController = supportFragmentManager.findFragmentById(R.id.main_frame)?.findNavController()

        if (intent.hasExtra("screen")){
            val inflater = navController?.navInflater
            val graph = inflater?.inflate(R.navigation.main_navigation_graph)
            graph?.setStartDestination(
                when(intent.getIntExtra("screen", 0)){
                    1 -> R.id.homeFragment
                    2 -> R.id.chatFragment
                    3 -> R.id.questFragment
                    else -> R.id.profileFragment
                }
            )
            graph?.let { navController?.graph = it }
        }

        navController?.let { binding.mainNavigationBar.setupWithNavController(it) }
    }
}