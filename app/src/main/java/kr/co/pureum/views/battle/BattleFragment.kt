package kr.co.pureum.views.battle

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.WaitingBattleAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentBattleBinding

@AndroidEntryPoint
class BattleFragment : BaseFragment<FragmentBattleBinding>(R.layout.fragment_battle) {
    private val viewModel by viewModels<BattleViewModel>()
    private val battleNavArgs by navArgs<BattleFragmentArgs>()

    private var _init = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (_init) checkNavArgs()
        initToolbar()
        initView()
        initListener()
        observe()

        if (requireActivity().intent.hasExtra("myChallenge")) {
            val action = BattleFragmentDirections.actionBattleFragmentToMyBattleFragment()
            findNavController().navigate(action)
            requireActivity().intent.removeExtra("myChallenge")
        }
    }

    private fun checkNavArgs() {
        when(battleNavArgs.toAllBattle) {
            true -> findNavController().navigate(BattleFragmentDirections.actionBattleFragmentToMyBattleFragment())
            else -> {}
        }
        when(requireActivity().intent.hasExtra("toMyBattle")) {
            true -> {
                val action = BattleFragmentDirections.actionBattleFragmentToMyBattleFragment()
                findNavController().navigate(action)
            }
            false -> {}
        }
        _init = false
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
    }

    private fun initView() {
        viewModel.getWaitingBattleInfo()
        with(binding) {
            isLoading = true
            battleWaitingRecyclerView.apply {
                adapter = WaitingBattleAdapter(requireContext()).apply {
                    addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (isLoading == false
                                && (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == itemCount - 1
                                && viewModel.waitingBattleListLiveData.value!!.size % 5 == 0
                            ) {
                                isLoading = true
                                viewModel.getMoreWaitingBattleInfo()
                            }
                        }
                    })
                    setListener(object : WaitingBattleAdapter.Listener {
                        override fun onClickRefuseButton(battleId: Long) {
                            isLoading = true
                            viewModel.refuseBattle(battleId)
                        }
                        override fun onClickAcceptButton(battleId: Long) {
                            isLoading = true
                            viewModel.acceptBattle(battleId)
                        }

                        override fun onClickWriteButton(battleId: Long) {
                            /*val action = BattleFragmentDirections.actionBattleFragmentToOnBattleSentenceFragment(battleId)
                            findNavController().navigate(action)*/
                            val intent = Intent(context, BattleAcceptActivity::class.java).apply {
                                putExtra("battleId", battleId)
                            }
                            Log.d("battleId", battleId.toString())
                            startActivity(intent)
                        }

                        override fun onClickCancelButton(battleId: Long) {
                            isLoading = true
                            viewModel.cancelBattle(battleId)
                        }
                    })
                }
                layoutManager = LinearLayoutManager(requireContext())
                isNestedScrollingEnabled = false
            }
        }
    }

    private fun initListener() {
        with(binding) {
            battleStartButton.setOnClickListener {
                startActivity(Intent(requireContext(), OnBattleActivity::class.java))
            }
            battleMyBattleButton.setOnClickListener {
                val action = BattleFragmentDirections.actionBattleFragmentToMyBattleFragment()
                findNavController().navigate(action)
            }
            battleAllBattleButton.setOnClickListener {
                val action = BattleFragmentDirections.actionBattleFragmentToAllBattleFragment()
                findNavController().navigate(action)
            }
            battleMoreButton.visibility = View.GONE
        }
    }

    private fun observe() {
        viewModel.waitingBattleListLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                (battleWaitingRecyclerView.adapter as WaitingBattleAdapter).setData(it)
                battleNoWaitingBattle.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                isLoading = false
                //battleLayout.requestLayout()
                //battleWaitingRecyclerView.layoutParams.height = battleWaitingRecyclerView.computeVerticalScrollRange()
                Log.e(TAG, "${battleWaitingRecyclerView.computeVerticalScrollRange()}")
            }
        }
        viewModel.battleControlResponseLiveData.observe(viewLifecycleOwner) {
            viewModel.getWaitingBattleInfo()
        }
    }
}