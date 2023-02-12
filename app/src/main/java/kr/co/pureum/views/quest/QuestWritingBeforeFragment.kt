package kr.co.pureum.views.quest

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestWritingBeforeBinding

@AndroidEntryPoint
class QuestWritingBeforeFragment : BaseFragment<FragmentQuestWritingBeforeBinding>(R.layout.fragment_quest_writing_before) {
    private lateinit var viewModel: QuestViewModel
    private var _keywords = listOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        viewModel = (requireParentFragment() as QuestClickFragment).viewModel
        binding.isLoading = true
        viewModel.getSentencesIncomplete()
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
            questTodaySentenceOneCv.setOnClickListener { if (_keywords.isNotEmpty()) viewModel.setKeyword(_keywords[0]) }
            questTodaySentenceTwoCv.setOnClickListener { if (_keywords.isNotEmpty()) viewModel.setKeyword(_keywords[1]) }
            questTodaySentenceThreeCv.setOnClickListener { if (_keywords.isNotEmpty()) viewModel.setKeyword(_keywords[2]) }
        }
    }
    private fun observe() {
        viewModel.todayKeywordListLiveData.observe(viewLifecycleOwner) {
            _keywords = it
            Log.d("tag", _keywords.toString())
            binding.keywords = _keywords
            Log.d("TAG", binding.keywords.toString())
            binding.isLoading = false
        }
        // 뒤로가기 이슈 (수정해야함)
        viewModel.keywordLiveData.observe(viewLifecycleOwner) {
            Log.e("TAG", it)
            val index = _keywords.indexOf(it)
            Log.e(ContentValues.TAG, index.toString())
            val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment(it, index)
            findNavController().navigate(action)
        }
    }
}