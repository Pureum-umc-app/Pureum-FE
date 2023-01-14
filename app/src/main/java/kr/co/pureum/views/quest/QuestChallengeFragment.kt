package kr.co.pureum.views.quest

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.domain.model.DataSentence
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataSentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestChallengeBinding

class QuestChallengeFragment : BaseFragment<FragmentQuestChallengeBinding>(R.layout.fragment_quest_challenge) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        Log.e("ScreenBuild", "QuestChallengeFragment")
        applySentenceView()
    }

    private fun applySentenceView() {
        val dataSentenceList : ArrayList<DataSentence> = arrayListOf()
        val dataSentenceAdapter = DataSentenceRVAdapter(dataSentenceList)

        val managerSentence = LinearLayoutManager(activity)
        managerSentence.reverseLayout = true
        managerSentence.stackFromEnd = true
        binding.questChallengeSentenceRv.layoutManager = managerSentence
        binding.questChallengeSentenceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.questChallengeSentenceRv.adapter = dataSentenceAdapter

        dataSentenceList.apply {
            add(DataSentence("성실"))
            add(DataSentence("바보"))
            add(DataSentence("호구"))
        }
    }
}