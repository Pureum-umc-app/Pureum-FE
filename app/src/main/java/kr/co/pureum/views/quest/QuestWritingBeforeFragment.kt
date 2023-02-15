package kr.co.pureum.views.quest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestWritingBeforeBinding

@AndroidEntryPoint
class QuestWritingBeforeFragment : BaseFragment<FragmentQuestWritingBeforeBinding>(R.layout.fragment_quest_writing_before) {
    private lateinit var viewModel: QuestViewModel
    private var _keywords = mutableListOf("", "", "")
    private var _wordId = mutableListOf<Long>()
    private var index: Int = 0
    private var wordId: Long = 0
    private var _size: Int = 0
    private lateinit var todayKeyword: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        viewModel = (requireParentFragment() as QuestClickFragment).viewModel
        viewModel.getSentencesIncomplete()
        Log.e("error", _keywords.size.toString())
        with(binding) {
            isLoading = true
        }
    }

    override fun onPause() {
        super.onPause()

    }

    private fun initListener() {
        with(binding) {
            questTodaySentenceOneCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[0])
                    viewModel.setWordId(wordId)
                    val action =
                        QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(
                            todayKeyword,
                            wordId,
                            index
                        )
                    findNavController().navigate(action)
                }
            }
            questTodaySentenceTwoCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[1])
                    viewModel.setWordId(wordId)
                    val action =
                        QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(
                            todayKeyword,
                            wordId,
                            index
                        )
                    Log.e(ContentValues.TAG, wordId.toString())
                    findNavController().navigate(action)
                }
            }
            questTodaySentenceThreeCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[2])
                    viewModel.setWordId(wordId)
                    val action =
                        QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(
                            todayKeyword,
                            wordId,
                            index
                        )
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun observe() {
        viewModel.incompleteKeywordIdListLiveData.observe(viewLifecycleOwner) {
            _wordId = it.toMutableList()
        }
        viewModel.incompleteKeywordListLiveData.observe(viewLifecycleOwner) {
            for (i in it.indices) {
                _keywords[i] = it[i]
            }
            _size = it.size
            with(binding) {
                keywords = it
                size = _size
                isLoading = false
            }
        }
        viewModel.keywordLiveData.observe(viewLifecycleOwner) {
            Log.e(ContentValues.TAG, it)
            Log.e(ContentValues.TAG, _keywords.toString())
            index = _keywords.indexOf(it)
            todayKeyword = it
            Log.e(ContentValues.TAG, "안녕 ${index}")
            Log.e(ContentValues.TAG, _wordId.toString())
            wordId = _wordId[index]
            binding.isLoading = false
        }
    }
}
