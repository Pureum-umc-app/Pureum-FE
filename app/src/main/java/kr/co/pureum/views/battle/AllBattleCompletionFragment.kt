package kr.co.pureum.views.battle

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.AllBattleCompletionAdapter
import kr.co.pureum.adapter.battle.MyBattleCompletionAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentAllBattleCompletionBinding
import kr.co.pureum.databinding.FragmentMyBattleCompletionBinding

@AndroidEntryPoint
class AllBattleCompletionFragment : BaseFragment<FragmentAllBattleCompletionBinding>(R.layout.fragment_all_battle_completion) {

    private val viewModel by viewModels<AllBattleCompletionViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        Log.e("ScreenBuild", "AllBattleCompletionFragment")
        with(binding) {
            viewModel.getAllBattleCompletionInfo()
            allBattleCompletionRv.apply {
                adapter = AllBattleCompletionAdapter().apply {
                    setListener(object : AllBattleCompletionAdapter.Listener{
                        override fun onItemClick(pos: Int, type: Int, itemId: Long) {
                            when (type) {
                                0 -> {
                                    val action = AllBattleFragmentDirections.actionAllBattleFragmentToAllBattleCompInfoDrawFragment(itemId)
                                    findNavController().navigate(action)
                                }
                                else -> {
                                    val action = AllBattleFragmentDirections.actionAllBattleFragmentToAllBattleCompInfoFragment(itemId)
                                    findNavController().navigate(action)
                                }
                            }

                        }
                    })
                }
                layoutManager = LinearLayoutManager(requireContext())

            }
        }
    }

    private fun initListener() {
        with(binding) {

        }
    }

    private fun observe() {
        viewModel.allBattleCompletionListLiveData.observe(viewLifecycleOwner) {
            (binding.allBattleCompletionRv.adapter as AllBattleCompletionAdapter).setData(it)
        }
    }

}