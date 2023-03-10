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
import kr.co.pureum.adapter.battle.MyBattleCompletionAdapter
import kr.co.pureum.adapter.battle.MyBattleProgressAdapter
import kr.co.pureum.adapter.battle.WaitingBattleAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentMyBattleProgressBinding
import kr.co.pureum.databinding.FragmentQuestWritingBeforeBinding
import kr.co.pureum.views.quest.QuestClickFragmentDirections

@AndroidEntryPoint
class MyBattleProgressFragment : BaseFragment<FragmentMyBattleProgressBinding>(R.layout.fragment_my_battle_progress) {

    private val viewModel by viewModels<MyBattleProgressViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        Log.e("ScreenBuild", "MyBattleProgressFragment")
        with(binding) {
            viewModel.getMyBattleProgressInfo()
            myBattleProgressRv.apply {
                adapter =  MyBattleProgressAdapter().apply {
                    setListener(object : MyBattleProgressAdapter.Listener{
                        override fun onItemClick(pos: Int, itemIdx: Long) {
                            val action = MyBattleFragmentDirections.actionMyBattleFragmentToMyBattleProgInfoFragment(itemIdx)
                            findNavController().navigate(action)
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
        viewModel.myBattleProgressListLiveData.observe(viewLifecycleOwner) {
            (binding.myBattleProgressRv.adapter as MyBattleProgressAdapter).setData(it)
        }
    }

}