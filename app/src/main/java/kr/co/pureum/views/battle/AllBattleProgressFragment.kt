package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.AllBattleProgressAdapter
import kr.co.pureum.adapter.battle.MyBattleProgressAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentAllBattleProgressBinding
import kr.co.pureum.databinding.FragmentMyBattleProgressBinding

@AndroidEntryPoint
class AllBattleProgressFragment : BaseFragment<FragmentAllBattleProgressBinding>(R.layout.fragment_all_battle_progress) {

    private val viewModel by viewModels<AllBattleProgressViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        Log.e("ScreenBuild", "MyBattleProgressFragment")
        with(binding) {
            viewModel.getAllBattleProgressInfo()
            allBattleProgressRv.apply {
                adapter =  AllBattleProgressAdapter().apply {
                    setListener(object : AllBattleProgressAdapter.Listener{
                        override fun onItemClick(pos: Int) {
//                            val action = AllBattleFragmentDirections.actionAllBattleFragmentToAllBattleProgInfoFragment()
//                            findNavController().navigate(action)
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
        viewModel.allBattleProgressListLiveData.observe(viewLifecycleOwner) {
            (binding.allBattleProgressRv.adapter as AllBattleProgressAdapter).setData(it)
        }
    }

}