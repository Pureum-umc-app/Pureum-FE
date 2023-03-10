package kr.co.pureum.views.battle

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.MyBattleCompletionAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentMyBattleCompletionBinding

@AndroidEntryPoint
class MyBattleCompletionFragment : BaseFragment<FragmentMyBattleCompletionBinding>(R.layout.fragment_my_battle_completion) {

    private val viewModel by viewModels<MyBattleCompletionViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        Log.e("ScreenBuild", "MyBattleCompletionFragment")
        with(binding) {
            viewModel.getMyBattleCompletionInfo()
            myBattleCompletionRv.apply {
                adapter = MyBattleCompletionAdapter().apply {
                    setListener(object : MyBattleCompletionAdapter.Listener{
                        override fun onItemClick(pos: Int, type: Int, itemId: Long) {
                            when (type) {
                                0 -> {
                                    val action = MyBattleFragmentDirections.actionMyBattleFragmentToMyBattleCompInfoFragment(itemId)
                                    findNavController().navigate(action)
                                }
                                1 -> {
                                    val action = MyBattleFragmentDirections.actionMyBattleFragmentToMyBattleCompInfoLoseFragment(itemId)
                                    findNavController().navigate(action)
                                }
                                else -> {
                                    val action = MyBattleFragmentDirections.actionMyBattleFragmentToMyBattleCompInfoDrawFragment(itemId)
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
        viewModel.myBattleCompletionListLiveData.observe(viewLifecycleOwner) {
            (binding.myBattleCompletionRv.adapter as MyBattleCompletionAdapter).setData(it)
        }
    }

}