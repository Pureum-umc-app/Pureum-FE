package kr.co.pureum.views.quest

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.domain.model.DataWrittenSentence
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataWrittenSentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestLatestSentenceBinding

class QuestLatestSentenceFragment : BaseFragment<FragmentQuestLatestSentenceBinding>(R.layout.fragment_quest_latest_sentence) {
    private val dataWrittenSentenceList : ArrayList<DataWrittenSentence> = arrayListOf()
    private val dataWrittenSentenceAdapter = DataWrittenSentenceRVAdapter(dataWrittenSentenceList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClickListener()
        initApplyRecyclerView()
    }

    private fun initView() {
    }

    private fun initClickListener() {
    }

    private fun initApplyRecyclerView() {
        val managerSentence = LinearLayoutManager(activity)
        managerSentence.reverseLayout = true
        managerSentence.stackFromEnd = true
        binding.questLatestSentenceRv.layoutManager = managerSentence
        binding.questLatestSentenceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.questLatestSentenceRv.adapter = dataWrittenSentenceAdapter


        dataWrittenSentenceList.apply {
            add(DataWrittenSentence("구현", R.drawable.ic_appicon_round, "르미", "방금", "3", "리사이클러뷰 구현에 성공했습니다","공개"))
            add(DataWrittenSentence("구현", R.drawable.ic_appicon_round, "르미", "방금", "3", "리사이클러뷰 구현에 성공했습니다", "공개"))
            add(DataWrittenSentence("구현", R.drawable.ic_appicon_round, "르미", "방금", "3", "리사이클러뷰 구현에 성공했습니다", "공개"))
            add(DataWrittenSentence("구현", R.drawable.ic_appicon_round, "르미", "방금", "3", "리사이클러뷰 구현에 성공했습니다", "공개"))
            add(DataWrittenSentence("구현", R.drawable.ic_appicon_round, "르미", "방금", "3", "리사이클러뷰 구현에 성공했습니다", "공개"))
            add(DataWrittenSentence("구현", R.drawable.ic_appicon_round, "르미", "방금", "3", "리사이클러뷰 구현에 성공했습니다", "공개"))
            add(DataWrittenSentence("구현", R.drawable.ic_appicon_round, "르미", "방금", "3", "리사이클러뷰 구현에 성공했습니다", "공개"))

        }

    }
}