package kr.co.pureum.views.quest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestWritingCompletionBinding

class QuestWritingCompletionFragment : BaseFragment<FragmentQuestWritingCompletionBinding>(R.layout.fragment_quest_writing_completion) {
    private lateinit var viewModel: QuestViewModel
    private var _keywords = mutableListOf("", "", "")
    private var _wordId = mutableListOf<Long>()
    private var wordId : Long = 0
    private var index: Int = 0
    var _size: Int = 0
    private lateinit var todayKeyword: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
    }

    private fun initView() {
        viewModel = (requireParentFragment() as QuestClickFragment).viewModel
        viewModel.getSentencesComplete()
        with(binding) {
            isLoading = true
        }
    }

    private fun initListener() {
        with(binding) {
            questCompletionSentenceOneCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    //viewModel.setCompleteKeyword(_keywords[size-3])
                    val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(todayKeyword, wordId, index)
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action)
                }
            }
            questCompletionSentenceTwoCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    //viewModel.setCompleteKeyword(_keywords[size-2])
                    val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(todayKeyword, wordId, index)
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action)
                }
            }

            questCompletionSentenceThreeCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    //viewModel.setCompleteKeyword(_keywords[size-1])
                    val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(todayKeyword,wordId, index)
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun observe() {
        viewModel.completeKeywordIdListLiveData.observe(viewLifecycleOwner) {
            _wordId = it as MutableList<Long>
        }
        viewModel.completeKeywordListLiveData.observe(viewLifecycleOwner) {
            Log.e(ContentValues.TAG, it.toString())
            for (i in it.indices) {
                _keywords[i] = it[i]
            }
            _size = it.size
            with(binding) {
                keywords = _keywords
                Log.e(ContentValues.TAG, keywords.toString())
                Log.e(ContentValues.TAG, _size.toString())
                binding.isLoading = false
            }
            initListener()
        }
        viewModel.completeKeywordLiveData.observe(viewLifecycleOwner) {
            todayKeyword = it
            index = _keywords.indexOf(it)
            wordId = _wordId[index]
            binding.isLoading = false
        }
    }
}
