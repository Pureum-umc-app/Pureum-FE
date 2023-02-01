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
        initBottomNavigation();
    }

    private var navController : NavController? = null
    private fun initBottomNavigation() {
        navController = supportFragmentManager.findFragmentById(R.id.main_frame)?.findNavController()?.apply {
            if (intent.hasExtra("screen")){
                graph = navInflater.inflate(R.navigation.main_navigation_graph).apply {
                    setStartDestination( when(intent.getIntExtra("screen", 0)){
                        1 -> R.id.homeFragment
                        2 -> R.id.battleFragment
                        3 -> R.id.questFragment
                        else -> R.id.profileFragment
                    })
                }
            }
            let { binding.mainNavigationBar.setupWithNavController(it) }
        }
    }
}