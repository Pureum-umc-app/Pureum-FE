package kr.co.pureum.views.quest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataWrittenSentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestLatestSentenceBinding

class QuestLatestSentenceFragment : BaseFragment<FragmentQuestLatestSentenceBinding>(R.layout.fragment_quest_latest_sentence) {
    private var wordId: Long = 0
    private val limit = 20
    private var index: Int = 0
    private var page = 0
    private val sort = "date"

    private lateinit var viewModel: QuestViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initApplyRecyclerView()
        initClickListener()
        observe()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initView() {
        viewModel = (requireParentFragment() as QuestVoidFragment).viewModel
        viewModel.getTodayKeyword()
        wordId = viewModel.keywordIdLiveData.value!!.toLong()
        viewModel.getSentencesIncomplete()
        viewModel.getSentencesComplete()
        viewModel.sentencesList(limit, page, sort, wordId)
        with(binding) {
            isLoading = true
        }
    }

    override fun onPause() {
        super.onPause()
    }
    private fun initClickListener() {
    }

    private fun initApplyRecyclerView() {
        val managerSentence = LinearLayoutManager(activity)
        managerSentence.reverseLayout = true
        managerSentence.stackFromEnd = true
        binding.questLatestSentenceRv.layoutManager = managerSentence
        binding.questLatestSentenceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.questLatestSentenceRv.adapter = DataWrittenSentenceRVAdapter()
    }

    private fun observe() {
        viewModel.todayKeywordIdListLiveData.observe(viewLifecycleOwner) {
            wordId = it[index]
            Log.e(ContentValues.TAG, wordId.toString())
        }
        viewModel.sentenceListLiveData.observe(viewLifecycleOwner) {
            (binding.questLatestSentenceRv.adapter as DataWrittenSentenceRVAdapter).setData(it!!)
            binding.isLoading = false
        }
    }
}