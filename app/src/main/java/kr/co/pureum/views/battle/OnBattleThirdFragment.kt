package kr.co.pureum.views.battle

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.BattleOpponentAdapter
import kr.co.pureum.adapter.battle.WaitingBattleAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBattleThirdBinding

@AndroidEntryPoint
class OnBattleThirdFragment : BaseFragment<FragmentOnBattleThirdBinding>(R.layout.fragment_on_battle_third) {
    private lateinit var viewModel: OnBattleViewModel
    private var opponentIndex = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        viewModel = (requireActivity() as OnBattleActivity).viewModel
        viewModel.getOpponentsList()
        with(binding) {
            isLoading = true
            battleOpponentRecyclerView.apply {
                adapter = BattleOpponentAdapter(requireContext()).apply {
                    setListener(object : BattleOpponentAdapter.Listener{
                        override fun onClick(checkedIndex: Int) {
                            battleOpponentButton.isEnabled = checkedIndex != -1
                            opponentIndex = checkedIndex
                        }
                    })
                }
                layoutManager = LinearLayoutManager(requireContext())
                itemAnimator = null
            }
            battleOpponentButton.isEnabled = false
        }
    }

    private fun initListener() {
        with(binding) {
            battleOpponentButton.setOnClickListener { viewModel.setOpponentWithIndex(opponentIndex) }
        }
    }

    private fun observe() {
        viewModel.opponentSLiveDate.observe(viewLifecycleOwner) {
            with(binding) {
                (battleOpponentRecyclerView.adapter as BattleOpponentAdapter).setData(it)
                isLoading = false
            }
        }
        viewModel.opponentLiveData.observe(viewLifecycleOwner) {
            Log.e(TAG, it.nickname)
            (requireActivity() as OnBattleActivity).navigate(OnBattleFourthFragment())
        }
    }
}