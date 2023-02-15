package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentAllBattleProgInfoBinding

@AndroidEntryPoint
class AllBattleProgInfoFragment : BaseFragment<FragmentAllBattleProgInfoBinding>(R.layout.fragment_all_battle_prog_info) {

    private val viewModel by viewModels<AllBattleProgInfoViewModel>()
    private val args : AllBattleProgInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
        initToolbar()
        battleLike()
    }

    private fun initView() {
        Log.e("ScreenBuild", "AllBattleProgInfoFragment")
        viewModel.getAllBattleProgMoreInfo(args.itemIdx)



    }

    private fun initListener() {
        with(binding) {

        }
    }

    private fun observe() {
        viewModel.allBattleProgressListLiveData.observe(viewLifecycleOwner) {
            binding.allBattleProgMoreDto = it

            Glide.with(binding.allBattleChallengedImg.context)
                .load(it.challengedImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.allBattleChallengedImg)

            Glide.with(binding.allBattleChallengerImg.context)
                .load(it.challengerImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.allBattleChallengerImg)

            Glide.with(binding.allBattleChallengedImgPro.context)
                .load(it.challengedImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.allBattleChallengedImgPro)

            Glide.with(binding.allBattleChallengerImgPro.context)
                .load(it.challengerImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.allBattleChallengerImgPro)

            when (it.challengerLike) {
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

            when (it.challengedLike) {
                1 -> {
                    binding.myBattleOpSentenceLike.isChecked = true
                }
                0 -> {
                    binding.myBattleOpSentenceLike.isChecked = false
                }
                else -> {
                    binding.myBattleOpSentenceLike.isChecked = false
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

    private fun battleLike(){
        viewModel.allBattleProgressListLiveData.observe(viewLifecycleOwner) {
            val firstUserId = it.challengerSentenceId
            val secondUserId = it.challengedSentenceId
            binding.myBattleSentenceLike.setOnClickListener{
                viewModel.postBattleLike(firstUserId, args.itemIdx)
            }
            binding.myBattleOpSentenceLike.setOnClickListener{
                viewModel.postBattleLike(secondUserId, args.itemIdx)
            }
        }

    }



}