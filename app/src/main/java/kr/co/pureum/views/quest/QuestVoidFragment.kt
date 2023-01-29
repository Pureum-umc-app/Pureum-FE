package kr.co.pureum.views.quest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.domain.model.DataWrittenSentence
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataWrittenSentenceRVAdapter
import kr.co.pureum.adapter.quest.QuestVoidVPAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestVoidBinding

class QuestVoidFragment : BaseFragment<FragmentQuestVoidBinding>(R.layout.fragment_quest_void) {
    private val dataWrittenSentenceList : ArrayList<DataWrittenSentence> = arrayListOf()
    private val dataWrittenSentenceAdapter = DataWrittenSentenceRVAdapter(dataWrittenSentenceList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar()
        initLayoutExamination()
        initClickListener()
        initViewPager()
    }

    private fun initView() {
    }

    override fun onResume() {
        super.onResume()
        initLayoutExamination()
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
        Log.d("Tag", dataWrittenSentenceAdapter.itemCount.toString())
        if (dataWrittenSentenceAdapter.itemCount == 0) {
            binding.questVoidCl.isVisible
            binding.questVoidExistCl.isGone
        }
        else if (dataWrittenSentenceAdapter.itemCount > 0){
            binding.questVoidCl.isGone
            binding.questVoidExistCl.isVisible
        }
    }
}