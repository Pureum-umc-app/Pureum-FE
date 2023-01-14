package kr.co.pureum.views.quest

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.QuestMainVPAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestBinding
import kr.co.pureum.views.MainActivity

class QuestFragment : BaseFragment<FragmentQuestBinding>(R.layout.fragment_quest) {
    lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
    }

    private fun initToolbar() {
        mainActivity = context as MainActivity
        with(mainActivity) {
            supportActionBar?.setDisplayUseLogoEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun initView() {
        Log.e("ScreenBuild", "QuestFragment")
        initViewPager()
    }

    // 뷰페이저 함수
    private fun initViewPager() {
        val questVPAdapter = QuestMainVPAdapter(this)
        binding.questViewpagerVp.adapter = questVPAdapter
        val tabNameArray = arrayListOf(
            "배지",
            "챌린지 도전하기"
        )
        TabLayoutMediator(binding.questTabTl, binding.questViewpagerVp) {
                tab, position -> tab.text = tabNameArray[position]
        }.attach()
    }

}