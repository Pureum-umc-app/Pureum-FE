package kr.co.pureum.views.quest


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.QuestClickVPAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestClickBinding
import kr.co.pureum.views.MainActivity

@AndroidEntryPoint
class QuestClickFragment : BaseFragment<FragmentQuestClickBinding>(R.layout.fragment_quest_click) {
    val viewModel by viewModels<QuestViewModel>()
    lateinit var mainActivity: MainActivity


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
        initView()
    }

    private fun initView() {
        Log.e("ScreenBuild", "QuestClickFragment")
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = androidx.core.content.ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    // 뷰페이저 함수
    private fun initViewPager() {
        val questClickVPAdapter = QuestClickVPAdapter(this)
        binding.questClickViewpagerVp.adapter = questClickVPAdapter
        binding.questClickViewpagerVp.isUserInputEnabled = false
        val tabNameArray = arrayListOf(
            "작성 전",
            "작성 완료"
        )
        TabLayoutMediator(binding.questClickTabTl, binding.questClickViewpagerVp) {
                tab, position -> tab.text = tabNameArray[position]
        }.attach()
    }

}

