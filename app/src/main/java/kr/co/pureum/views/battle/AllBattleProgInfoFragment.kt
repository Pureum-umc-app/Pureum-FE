package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentAllBattleProgInfoBinding

@AndroidEntryPoint
class AllBattleProgInfoFragment : BaseFragment<FragmentAllBattleProgInfoBinding>(R.layout.fragment_all_battle_prog_info) {

    private val viewModel by viewModels<AllBattleProgInfoViewModel>()
    private val args : AllBattleProgInfoFragmentArgs by navArgs()

    private val itemId : Long = args.itemIdx
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
        initToolbar()
    }

    private fun initView() {
        Log.e("ScreenBuild", "AllBattleProgInfoFragment")
        viewModel.getAllBattleProgressInfo(itemId)
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