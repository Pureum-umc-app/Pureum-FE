package kr.co.pureum.views.quest

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.domain.model.DataSentence
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataSentenceRVAdapter
import kr.co.pureum.adapter.quest.DataTodaySentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestWritingBeforeBinding

class QuestWritingBeforeFragment : BaseFragment<FragmentQuestWritingBeforeBinding>(R.layout.fragment_quest_writing_before) {

    private val dataSentenceList : ArrayList<DataSentence> = arrayListOf()
    private val dataSentenceAdapter = DataTodaySentenceRVAdapter(dataSentenceList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initView() {

    }

    private fun initRecyclerView() {
        val managerRecyclerView = LinearLayoutManager(activity)
        managerRecyclerView.reverseLayout = true
        managerRecyclerView.stackFromEnd = true
        binding.questWritingBeforeRv.layoutManager = managerRecyclerView
        binding.questWritingBeforeRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.questWritingBeforeRv.adapter = dataSentenceAdapter

        dataSentenceList.apply {
            add(DataSentence("오늘의 키워드 1", "성실"))
            add(DataSentence("오늘의 키워드 2", "바보"))
            add(DataSentence("오늘의 키워드 3", "호구"))
        }
    }
}