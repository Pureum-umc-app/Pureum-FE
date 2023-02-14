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
import kr.co.pureum.databinding.FragmentAllBattleCompInfoDrawBinding
import kr.co.pureum.databinding.FragmentMyBattleCompInfoDrawBinding

@AndroidEntryPoint
class AllBattleCompInfoDrawFragment : BaseFragment<FragmentAllBattleCompInfoDrawBinding>(R.layout.fragment_all_battle_comp_info_draw) {

    private val viewModel by viewModels<AllBattleCompInfoViewModel>()
    private val args : AllBattleCompInfoDrawFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
        initToolbar()
    }

    private fun initView() {
        Log.e("ScreenBuild", "AllBattleCompInfoDrawFragment")
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

            Glide.with(binding.battleFirstProfile.context)
                .load(it.winnerImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.battleFirstProfile)

            Glide.with(binding.battleSecondProfile.context)
                .load(it.loserImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.battleSecondProfile)

            when (it.userLike) {
                1 -> {
                    binding.drawMyBattleSentenceLike.setImageResource(R.drawable.ic_battle_heart_fill)
                }
                0 -> {
                    binding.drawMyBattleSentenceLike.setImageResource(R.drawable.ic_battle_heart_not_fill)
                }
                else -> {
                    binding.drawMyBattleSentenceLike.setImageResource(R.drawable.ic_battle_heart_not_fill)
                }
            }

            when (it.oppLike) {
                1 -> {
                    binding.drawMyBattleOpSentenceLike.setImageResource(R.drawable.ic_battle_heart_fill)
                }
                0 -> {
                    binding.drawMyBattleOpSentenceLike.setImageResource(R.drawable.ic_battle_heart_not_fill)
                }
                else -> {
                    binding.drawMyBattleOpSentenceLike.setImageResource(R.drawable.ic_battle_heart_not_fill)
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