package kr.co.pureum.views.quest

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataWrittenSentenceRVAdapter
import kr.co.pureum.adapter.quest.QuestVoidVPAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestVoidBinding

@AndroidEntryPoint
class QuestVoidFragment : BaseFragment<FragmentQuestVoidBinding>(R.layout.fragment_quest_void) {
    private val arg : QuestVoidFragmentArgs by navArgs()
    private lateinit var _keyword: String
    val viewModel by viewModels<QuestViewModel>()
    private val dataWrittenSentenceAdapter = DataWrittenSentenceRVAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar()
        initClickListener()
        initViewPager()
        observe()
    }

    private fun initView() {
        val todayKeyword = arg.todayKeyword
        val wordId = arg.wordId
        viewModel.setWordId(wordId)
        Log.e(ContentValues.TAG, wordId.toString())
        viewModel.setKeyword(todayKeyword)
        viewModel.getTodayKeyword()
        viewModel.sentencesList(20, 0, "date", wordId)
        binding.isLoading = true
    }

    private fun observe() {
        viewModel.sentenceListLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                dataWrittenSentenceAdapter.setData(it)
                initLayoutExamination()
            }
            binding.isLoading = false
        }
        viewModel.keywordLiveData.observe(viewLifecycleOwner) {
            _keyword = it
            with(binding) {
                keyword = it
                initLayoutExamination()
                binding.isLoading = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initLayoutExamination()
    }

    private fun initClickListener() {
        with(binding) {
            questWritingSentenceBt.setOnClickListener() {
                val intent = Intent(activity, QuestWriteSentenceActivity::class.java).apply {
                    putExtra("keyword", keyword)
                    putExtra("index", arg.index)
                }
                startActivity(intent)
        }
            questExistWriteSentenceBt.setOnClickListener {
                val intent = Intent(activity, QuestWriteSentenceActivity::class.java).apply {
                    putExtra("keyword", keyword)
                    putExtra("index", arg.index)
                }
                startActivity(intent)
            }
        }
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initViewPager() {
        val questClickVPAdapter = QuestVoidVPAdapter(this)
        binding.questVoidViewpagerVp.adapter = questClickVPAdapter
        binding.questVoidViewpagerVp.isUserInputEnabled = false
        val tabNameArray = arrayListOf(
            "최신순",
            "인기순"
        )
        TabLayoutMediator(binding.questVoidTabTl, binding.questVoidViewpagerVp) {
                tab, position -> tab.text = tabNameArray[position]
        }.attach()
    }

    private fun initLayoutExamination() {
        Log.d("Tag", dataWrittenSentenceAdapter.itemCount.toString())
        when(dataWrittenSentenceAdapter.itemCount) {
            0 -> {
                binding.questVoidCl.isVisible = true
                binding.questVoidExistCl.isGone = true
            }
            else -> {
                binding.questVoidCl.isGone = true
                binding.questVoidExistCl.isVisible = true
            }
        }
        binding.isLoading = true
    }
}