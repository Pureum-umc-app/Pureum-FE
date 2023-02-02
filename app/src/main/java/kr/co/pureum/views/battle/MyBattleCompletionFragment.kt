package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.MyBattleCompletionAdapter
import kr.co.pureum.adapter.battle.MyBattleProgressAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentMyBattleCompletionBinding
import kr.co.pureum.databinding.FragmentMyBattleProgressBinding

@AndroidEntryPoint
class MyBattleCompletionFragment : BaseFragment<FragmentMyBattleCompletionBinding>(R.layout.fragment_my_battle_completion) {

    private val viewModel by viewModels<MyBattleCompletionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        Log.e("ScreenBuild", "MyBattleCompletionFragment")
        with(binding) {
            viewModel.getMyBattleCompletionInfo()
            myBattleCompletionRv.apply {
                adapter = MyBattleCompletionAdapter()
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