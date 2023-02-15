package kr.co.pureum.views.battle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBattleSentenceBinding

class OnBattleSentenceFragment : BaseFragment<FragmentOnBattleSentenceBinding>(R.layout.fragment_on_battle_sentence) {
    private val viewModel by viewModels<OnBattleViewModel>()
    private val battleNavArgs by navArgs<OnBattleSentenceFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        
    }

    private fun initListener() {

    }

    private fun observe() {

    }
}