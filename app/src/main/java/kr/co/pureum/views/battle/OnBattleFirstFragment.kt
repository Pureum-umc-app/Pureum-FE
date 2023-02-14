package kr.co.pureum.views.battle

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBattleFirstBinding

@AndroidEntryPoint
class OnBattleFirstFragment : BaseFragment<FragmentOnBattleFirstBinding>(R.layout.fragment_on_battle_first) {
    private lateinit var viewModel: OnBattleViewModel
    private var myKeywords: List<String> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        viewModel = (requireActivity() as OnBattleActivity).viewModel
        viewModel.getThreeKeywords()
        binding.isLoading = true
    }

    private fun initListener() {
        with(binding) {
            mutableListOf(battleKeyword1, battleKeyword2, battleKeyword3).forEachIndexed { index, it ->
                it.setOnClickListener { viewModel.setKeyword(myKeywords[index]) }
            }
        }
    }

    private fun observe() {
        viewModel.keywordsLiveData.observe(viewLifecycleOwner) {
            myKeywords = it.map { keyword -> keyword.word }
            with(binding) {
                keywords = myKeywords
                isLoading = false
            }
        }
        viewModel.keywordLiveData.observe(viewLifecycleOwner) {
            Log.e(TAG, it.word)
            (requireActivity() as OnBattleActivity).navigate(OnBattleSecondFragment())
        }
    }
}