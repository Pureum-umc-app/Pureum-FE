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
import kr.co.pureum.databinding.FragmentMyBattleCompInfoBinding
import kr.co.pureum.databinding.FragmentMyBattleProgInfoBinding

@AndroidEntryPoint
class MyBattleCompInfoFragment : BaseFragment<FragmentMyBattleCompInfoBinding>(R.layout.fragment_my_battle_comp_info) {

    private val viewModel by viewModels<MyBattleCompInfoViewModel>()
    private val args : MyBattleCompInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
        initToolbar()
    }

    private fun initView() {
        Log.e("ScreenBuild", "MyBattleCompInfoFragment")
        viewModel.getMyBattleCompInfo(args.itemId)
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

            Glide.with(binding.battleWinnerProfile.context)
                .load(it.winnerImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.battleWinnerProfile)

            Glide.with(binding.battleLoserProfile.context)
                .load(it.loserImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.battleLoserProfile)

            when (it.selfLike) {
                1 -> {
                    binding.battleWinnerSentenceLike.setImageResource(R.drawable.ic_battle_heart_fill)
                }
                0 -> {
                    binding.battleWinnerSentenceLike.setImageResource(R.drawable.ic_battle_heart_not_fill)
                }
                else -> {
                    binding.battleWinnerSentenceLike.setImageResource(R.drawable.ic_battle_heart_not_fill)
                }
            }

            when (it.oppLike) {
                1 -> {
                    binding.myBattleSentenceLike.setImageResource(R.drawable.ic_battle_heart_fill)
                }
                0 -> {
                    binding.myBattleSentenceLike.setImageResource(R.drawable.ic_battle_heart_not_fill)
                }
                else -> {
                    binding.myBattleSentenceLike.setImageResource(R.drawable.ic_battle_heart_not_fill)
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