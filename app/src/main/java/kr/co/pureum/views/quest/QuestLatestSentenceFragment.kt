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
    private lateinit var _keyword: List<String>
    private lateinit var _wordId: List<Long>
    private lateinit var keyword: String
    private var wordId: Long = 0
    private val limit = 20
    private var index: Int = 0
    private var page = 0
    private val sort = "date"

    private val dataWrittenSentenceAdapter = DataWrittenSentenceRVAdapter()
    private lateinit var viewModel: QuestViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClickListener()
        initApplyRecyclerView()
        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.sentencesList(limit, page, sort, wordId)
    }

    private fun initView() {
        viewModel = (requireParentFragment() as QuestVoidFragment).viewModel
        viewModel.getTodayKeyword()
        keyword = viewModel.keywordLiveData.value.toString()
        viewModel.getSentencesIncomplete()
        viewModel.getSentencesComplete()
        viewModel.sentencesList(limit, page, sort, wordId)
        with(binding) {
            isLoading = true
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e(ContentValues.TAG, wordId.toString())
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
    }

    private fun observe() {
        viewModel.todayKeywordListLiveData.observe(viewLifecycleOwner) {
            _keyword = it
            index = it.indexOf(keyword)
        }
        viewModel.completeKeywordIdListLiveData.observe(viewLifecycleOwner) {
            wordId = it[index]
            binding.isLoading = false
        }
        viewModel.sentenceListLiveData.observe(viewLifecycleOwner) {
            (binding.questLatestSentenceRv.adapter as DataWrittenSentenceRVAdapter).setData(it!!)
            binding.isLoading = false
        }
    }
}