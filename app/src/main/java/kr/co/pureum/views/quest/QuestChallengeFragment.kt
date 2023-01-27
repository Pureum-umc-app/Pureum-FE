package kr.co.pureum.views.quest

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.domain.model.DataSentence
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataSentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestChallengeBinding
import kr.co.pureum.views.MainActivity

class QuestChallengeFragment : BaseFragment<FragmentQuestChallengeBinding>(R.layout.fragment_quest_challenge) {
    lateinit var mainActivity : MainActivity
    private val dataSentenceList : ArrayList<DataSentence> = arrayListOf()
    private val dataSentenceAdapter = DataSentenceRVAdapter(dataSentenceList)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initApplySentenceView()
    }

    private fun initView() {
        Log.e("ScreenBuild", "QuestChallengeFragment")
    }

    private fun initListener() {
        with(binding) {
            questChallengeSentenceCv.setOnClickListener {
                val action = QuestFragmentDirections.actionQuestFragmentToQuestClickFragment()
                findNavController().navigate(action)
            }
        }
    }
    // 리사이클러뷰 sentence 함수
    private fun initApplySentenceView() {

        val managerSentence = LinearLayoutManager(activity)
        managerSentence.reverseLayout = true
        managerSentence.stackFromEnd = true
        binding.questChallengeSentenceRv.layoutManager = managerSentence
        binding.questChallengeSentenceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.questChallengeSentenceRv.adapter = dataSentenceAdapter

        dataSentenceList.clear()
        dataSentenceList.apply {
            add(DataSentence( "성실"))
            add(DataSentence("바보"))
            add(DataSentence( "호구"))
        }
    }
}