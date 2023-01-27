package kr.co.pureum.views.quest

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.QuestMainVPAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestBinding
import kr.co.pureum.views.MainActivity

class QuestFragment : BaseFragment<FragmentQuestBinding>(R.layout.fragment_quest) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initViewPager()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
    }

    private fun initView() {
        Log.e("ScreenBuild", "QuestFragment")
    }

    // 뷰페이저 함수
    private fun initViewPager() {
        val questVPAdapter = QuestMainVPAdapter(this)
        binding.questViewpagerVp.adapter = questVPAdapter
        binding.questViewpagerVp.isUserInputEnabled = false
        val tabNameArray = arrayListOf(
            "배지",
            "챌린지 도전하기"
        )
        TabLayoutMediator(binding.questTabTl, binding.questViewpagerVp) {
                tab, position -> tab.text = tabNameArray[position]
        }.attach()
    }
}