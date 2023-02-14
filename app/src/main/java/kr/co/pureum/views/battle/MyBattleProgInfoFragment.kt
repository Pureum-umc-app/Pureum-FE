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

    private val viewModel by viewModels<AllBattleProgInfoViewModel>()
    private val args : MyBattleProgInfoFragmentArgs by navArgs()
    private val itemId : Long = args.itemIdx

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
        initToolbar()
    }

    private fun initView() {
        Log.e("ScreenBuild", "MyBattleProgInfoFragment")
        viewModel.getAllBattleProgressInfo(itemId)
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
        viewModel.allBattleProgressListLiveData.observe(viewLifecycleOwner) {
            binding.allBattleProgMoreDto = it

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
                dialog.dismiss()
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



}