package kr.co.pureum.views.battle

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.MyBattleCompletionAdapter
import kr.co.pureum.adapter.battle.MyBattleProgressAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.DialogDefaultBinding
import kr.co.pureum.databinding.FragmentMyBattleProgInfoBinding
import kr.co.pureum.databinding.FragmentMyBattleProgressBinding
import kr.co.pureum.views.MainActivity
import kr.co.pureum.views.home.HomeFragment

@AndroidEntryPoint
class MyBattleProgInfoFragment : BaseFragment<FragmentMyBattleProgInfoBinding>(R.layout.fragment_my_battle_prog_info) {

    private val viewModel by viewModels<MyBattleProgInfoViewModel>()
    private val args : MyBattleProgInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
        initToolbar()
        battleLike()
    }

    private fun initView() {
        Log.e("ScreenBuild", "MyBattleProgInfoFragment")
        viewModel.getMyBattleProgMoreInfo(args.itemIdx)
        with(binding) {
        }

    }

    private fun initListener() {
        with(binding) {
            myBattleCancelButton.setOnClickListener{
                showDeleteDialog()
            }
        }
    }

    private fun observe() {
        viewModel.myBattleProgressListLiveData.observe(viewLifecycleOwner) {
            binding.myBattleProgMoreDto = it

            Glide.with(binding.myProfile.context)
                .load(it.myImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.myProfile)

            Glide.with(binding.oppProfile.context)
                .load(it.oppImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.oppProfile)

            Glide.with(binding.myProfileImg.context)
                .load(it.myImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.myProfileImg)

            Glide.with(binding.oppProfileImg.context)
                .load(it.oppImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.oppProfileImg)

            when (it.myLike) {
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

    private fun showDeleteDialog() {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogDefaultBinding.inflate(LayoutInflater.from(requireContext()))
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_20dp)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding) {
            titleText = "대결을 취소하시겠습니까?"
            cancelText = "아니오"
            confirmText = "네"
            dialogCancelButton.setOnClickListener { dialog.dismiss() }
            dialogConfirmButton.setOnClickListener {
                viewModel.cancelBattle(args.itemIdx)
                dialog.dismiss()
                findNavController().navigateUp()

            }
            dialog.show()
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
        viewModel.myBattleProgressListLiveData.observe(viewLifecycleOwner) {
            val firstUserId = it.mySentenceId
            val secondUserId = it.oppSentenceId
            binding.myBattleSentenceLike.setOnClickListener{
                viewModel.postBattleLike(firstUserId)
            }
            binding.myBattleOpSentenceLike.setOnClickListener{
                viewModel.postBattleLike(secondUserId)
            }
        }

    }



}