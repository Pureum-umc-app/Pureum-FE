package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentMyBattleCompInfoBinding
import kr.co.pureum.databinding.FragmentMyBattleCompInfoLoseBinding

@AndroidEntryPoint
class MyBattleCompInfoLoseFragment : BaseFragment<FragmentMyBattleCompInfoLoseBinding>(R.layout.fragment_my_battle_comp_info_lose) {

    private val viewModel by viewModels<MyBattleCompInfoViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        Log.e("ScreenBuild", "MyBattleCompInfoLoseFragment")
        viewModel.getMyBattleCompInfo()
        with(binding) {

        }

    }

    private fun initListener() {
        with(binding) {

        }
    }

    private fun observe() {
        viewModel.myBattleCompListLiveData.observe(viewLifecycleOwner) {
            binding.myBattleCompMoreDto = it
        }
    }

}