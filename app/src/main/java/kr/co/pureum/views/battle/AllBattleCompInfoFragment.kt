package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentAllBattleCompInfoBinding
import kr.co.pureum.databinding.FragmentMyBattleCompInfoBinding

@AndroidEntryPoint
class AllBattleCompInfoFragment : BaseFragment<FragmentAllBattleCompInfoBinding>(R.layout.fragment_all_battle_comp_info) {

    private val viewModel by viewModels<AllBattleCompInfoViewModel>()
    private val args : AllBattleCompInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
        initToolbar()
    }

    private fun initView() {
        Log.e("ScreenBuild", "AllBattleCompInfoFragment")
        viewModel.getAllBattleCompMoreInfo(args.itemId)
        with(binding) {

        }

    }

    private fun initListener() {
        with(binding) {

        }
    }

    private fun observe() {
        viewModel.allBattleCompListLiveData.observe(viewLifecycleOwner) {
            binding.allBattleCompMoreDto = it

            Glide.with(binding.battleWinnerProfile.context)
                .load(it.winnerImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.battleWinnerProfile)

            Glide.with(binding.battleLoserProfile.context)
                .load(it.loserImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.battleLoserProfile)

            when (it.userLike) {
                1 -> {
                    binding.myBattleSentenceLike.isChecked = true
                }
                0 -> {
                    binding.myBattleSentenceLike.isChecked = false
                }
                else -> {
                    binding.myBattleSentenceLike.isChecked = false
                }
            }

            when (it.oppLike) {
                1 -> {
                    binding.myBattleSentenceLike.isChecked = true
                }
                0 -> {
                    binding.myBattleSentenceLike.isChecked = false
                }
                else -> {
                    binding.myBattleSentenceLike.isChecked = false
                }
            }
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