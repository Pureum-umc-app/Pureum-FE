package kr.co.pureum.views.quest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestWritingBeforeBinding

@AndroidEntryPoint
class QuestWritingBeforeFragment : BaseFragment<FragmentQuestWritingBeforeBinding>(R.layout.fragment_quest_writing_before) {
    private lateinit var viewModel: QuestViewModel
    private var _keywords = listOf<String>()
    private var _wordId = mutableListOf<Long>()
    private var index: Int = 0
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
        Log.e("error", _keywords.toString())
        with(binding) {
            when(_keywords.size) {
                2 -> questTodaySentenceThreeCv.isGone = true
                1 -> {
                    questTodaySentenceTwoCv.isGone = true
                    questTodaySentenceThreeCv.isGone = true
                }
                0 -> {
                    questTodaySentenceOneCv.isGone = true
                    questTodaySentenceTwoCv.isGone = true
                    questTodaySentenceThreeCv.isGone = true
                }
            }
            isLoading = true
        }
    }

    override fun onPause() {
        super.onPause()

    }

    private fun initListener() {
        with(binding) {
            /*
            questTodaySentenceOneCv.setOnClickListener {
                val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment()
                findNavController().navigate(action)
            }

             */
            questTodaySentenceOneCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[0])
                    val action =
                        QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(
                            todayKeyword,
                            index
                        )
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action)
                }
            }
            questTodaySentenceTwoCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[1])
                    val action =
                        QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(
                            todayKeyword,
                            index
                        )
                    Log.e(ContentValues.TAG, todayKeyword)
                    findNavController().navigate(action)
                }
            }
            questTodaySentenceThreeCv.setOnClickListener {
                if (_keywords.isNotEmpty()) {
                    viewModel.setKeyword(_keywords[2])
                    val action =
                        QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(
                            todayKeyword,
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
            Log.e(ContentValues.TAG, _wordId.toString())
        }
        viewModel.incompleteKeywordListLiveData.observe(viewLifecycleOwner) {
            _keywords = it
            Log.d("tag", _keywords.toString())
            with(binding) {
                keywords = _keywords
                Log.d("TAG", keywords.toString())
                isLoading = false
            }
            viewModel.keywordLiveData.observe(viewLifecycleOwner) {
                index = _keywords.indexOf(it)
                todayKeyword = it
            }
        }
    }
}
