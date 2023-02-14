package kr.co.pureum.views.quest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestWritingCompletionBinding

class QuestWritingCompletionFragment : BaseFragment<FragmentQuestWritingCompletionBinding>(R.layout.fragment_quest_writing_completion) {
    private lateinit var viewModel: QuestViewModel
    private var _keywords = listOf<String>()
    private var _wordId = mutableListOf<Long>()
    private var index: Int = 0
    var size: Int = 0
    val sentenceId: Long = 0
    private lateinit var todayKeyword: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        viewModel = (requireParentFragment() as QuestClickFragment).viewModel
        viewModel.getSentencesComplete()
        with(binding) {
            keywords = _keywords
            Log.e(ContentValues.TAG, _keywords.toString())
            // 코드 보완 필!
            when(_keywords.size) {
                1 -> questTodaySentenceOneCv.isVisible = true
                2 -> {
                    questTodaySentenceTwoCv.isVisible = true
                    questTodaySentenceOneCv.isVisible = true
                }
                else -> {
                    questTodaySentenceOneCv.isVisible = true
                    questTodaySentenceTwoCv.isVisible = true
                    questTodaySentenceThreeCv.isVisible = true
                }
            }
            isLoading = true
        }
    }

    private fun initListener() {
        with(binding) {
            questTodaySentenceOneCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[0])
                    val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(todayKeyword, index)
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action)
                }
            }
            questTodaySentenceTwoCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[1])
                    val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(todayKeyword, index)
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action)
                }
            }

            questTodaySentenceThreeCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[2])
                    val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(todayKeyword, index)
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action) }
                }
            }
        }
    private fun observe() {
        viewModel.completeKeywordListLiveData.observe(viewLifecycleOwner) {
            Log.e(ContentValues.TAG, it.toString())
            _keywords = it
            size = _keywords.size
            with(binding) {
                keywords = it
                Log.e(ContentValues.TAG, keywords.toString())
                binding.isLoading = false
            }
        }
        viewModel.completeKeywordIdListLiveData.observe(viewLifecycleOwner) {
            _wordId = it.toMutableList().subList(0, size)
            Log.e(ContentValues.TAG, _wordId.toString())
            binding.isLoading = false
        }
        viewModel.keywordLiveData.observe(viewLifecycleOwner) {
            index = _keywords.indexOf(it)
            Log.e(ContentValues.TAG, index.toString())
            todayKeyword = it
            binding.isLoading = false
        }
    }
}
