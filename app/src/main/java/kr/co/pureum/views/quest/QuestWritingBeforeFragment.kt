package kr.co.pureum.views.quest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestWritingBeforeBinding

@AndroidEntryPoint
class QuestWritingBeforeFragment : BaseFragment<FragmentQuestWritingBeforeBinding>(R.layout.fragment_quest_writing_before) {
    private val viewModel by viewModels<QuestViewModel>()
    private var _keywords = listOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initView()
        initListener()
    }

    private fun initView() {
    }

    private fun initListener() {
        with(binding) {
            questTodaySentenceOneCv.setOnClickListener {
                val action = QuestClickFragmentDirections.actionQuestClickFragmentToQuestVoidFragment()
                findNavController().navigate(action)
            }
        }
    }
    private fun observe() {
        viewModel.keywordLiveData.observe(viewLifecycleOwner) {
        }
    }

}