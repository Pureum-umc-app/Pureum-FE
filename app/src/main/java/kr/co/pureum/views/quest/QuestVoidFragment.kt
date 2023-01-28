package kr.co.pureum.views.quest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.QuestVoidVPAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestVoidBinding
import kr.co.pureum.views.MainActivity

class QuestVoidFragment : BaseFragment<FragmentQuestVoidBinding>(R.layout.fragment_quest_void) {
    lateinit var mainActivity : MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar()
        initClickListener()
        initViewPager()
        initLayoutExamination()
    }

    private fun initView() {
        binding.questWritingSentenceBt.text = "문장 작성하러 가기"
    }

    private fun initClickListener() {
        binding.questWritingSentenceBt.setOnClickListener() {
            val intent = Intent(activity, QuestWriteSentenceActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initViewPager() {
        val questClickVPAdapter = QuestVoidVPAdapter(this)
        binding.questVoidViewpagerVp.adapter = questClickVPAdapter
        binding.questVoidViewpagerVp.isUserInputEnabled = false
        val tabNameArray = arrayListOf(
            "최신순",
            "인기순"
        )
        TabLayoutMediator(binding.questVoidTabTl, binding.questVoidViewpagerVp) {
                tab, position -> tab.text = tabNameArray[position]
        }.attach()
    }

    private fun initLayoutExamination() {

    }
}