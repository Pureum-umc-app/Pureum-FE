package kr.co.pureum.views.battle

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.DialogDefaultBinding
import kr.co.pureum.databinding.FragmentAllBattleProgInfoBinding
import kr.co.pureum.databinding.FragmentMyBattleProgInfoBinding

@AndroidEntryPoint
class AllBattleProgInfoFragment : BaseFragment<FragmentAllBattleProgInfoBinding>(R.layout.fragment_all_battle_prog_info) {

    private val viewModel by viewModels<AllBattleProgInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
        initToolbar()
    }

    private fun initView() {
        Log.e("ScreenBuild", "AllBattleProgInfoFragment")
        viewModel.getAllBattleProgressInfo()
        with(binding) {
        }

    }

    private fun initListener() {
        with(binding) {

        }
    }

    private fun observe() {
        viewModel.allBattleProgressListLiveData.observe(viewLifecycleOwner) {
            binding.allBattleProgMoreDto = it

        }
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = androidx.core.content.ContextCompat.getDrawable(context, kr.co.pureum.R.drawable.ic_back)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }



}