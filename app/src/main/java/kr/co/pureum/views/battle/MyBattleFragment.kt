package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.MyBattleVPAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentMyBattleBinding
import kr.co.pureum.views.MainActivity

class MyBattleFragment : BaseFragment<FragmentMyBattleBinding>(R.layout.fragment_my_battle) {
    lateinit var mainActivity: MainActivity


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar()
        initViewPager()
    }

    private fun initView() {
        Log.e("ScreenBuild", "MyBattleFragment")
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = androidx.core.content.ContextCompat.getDrawable(context, kr.co.pureum.R.drawable.ic_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initViewPager() {
        val myBattleVPAdapter = MyBattleVPAdapter(this)
        binding.battleMyViewpagerVp.adapter = myBattleVPAdapter
        binding.battleMyViewpagerVp.isUserInputEnabled = false
        val tabNameArray = arrayListOf(
            "진행 중인 대결",
            "종료된 대결"
        )
        TabLayoutMediator(binding.battleMyTabTl, binding.battleMyViewpagerVp) {
                tab, position -> tab.text = tabNameArray[position]
        }.attach()
    }

}