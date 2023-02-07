package kr.co.pureum.views.quest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.BottomSheetBadgeBinding
import kr.co.pureum.databinding.FragmentQuestBadgeBinding

@AndroidEntryPoint
class QuestBadgeFragment : BaseFragment<FragmentQuestBadgeBinding>(R.layout.fragment_quest_badge) {
    private val viewModel by viewModels<QuestBadgeViewModel>()
    private var badgeList = listOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initListener()
        observe()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun initView() {
        binding.isLoading = true
        viewModel.getBadgeInfo()
    }

    private fun initListener() {
        with(binding) {
            badge1.setOnClickListener { showBadgeInfoDialog(1) }
            badge2.setOnClickListener { showBadgeInfoDialog(2) }
            badge3.setOnClickListener { showBadgeInfoDialog(3) }
            badge4.setOnClickListener { showBadgeInfoDialog(4) }
            badge5.setOnClickListener { showBadgeInfoDialog(5) }
            badge6.setOnClickListener { showBadgeInfoDialog(6) }
            badge7.setOnClickListener { showBadgeInfoDialog(7) }
            badge8.setOnClickListener { showBadgeInfoDialog(8) }
            badge9.setOnClickListener { showBadgeInfoDialog(9) }
        }
    }

    private fun observe() {
        viewModel.badgeListLiveData.observe(viewLifecycleOwner) {
            badgeList = it
            with(binding) {
                recentBadge = resources.getText(R.string.badge_name_1).toString()
                badgeCount = it.count { elem -> elem == -1 }
                badgeUnlocked = it
                isLoading = false
            }
        }
    }

    private fun showBadgeInfoDialog(index: Int) {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        val dialogBinding =
            BottomSheetBadgeBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setContentView(dialogBinding.root)
        with(dialogBinding) {
            badgeIndex = index
            badgeUnlocked = badgeList
            badgeDialogInfoText.text = resources.getText(when(index) {
                1 -> if(badgeList[index] == -1) R.string.badge_info_1_true else R.string.badge_info_1_false
                2 -> if(badgeList[index] == -1) R.string.badge_info_2_true else R.string.badge_info_2_false
                3 -> if(badgeList[index] == -1) R.string.badge_info_3_true else R.string.badge_info_3_false
                4 -> if(badgeList[index] == -1) R.string.badge_info_4_true else R.string.badge_info_4_false
                5 -> if(badgeList[index] == -1) R.string.badge_info_5_true else R.string.badge_info_5_false
                6 -> if(badgeList[index] == -1) R.string.badge_info_6_true else R.string.badge_info_6_false
                7 -> if(badgeList[index] == -1) R.string.badge_info_7_true else R.string.badge_info_7_false
                8 -> if(badgeList[index] == -1) R.string.badge_info_8_true else R.string.badge_info_8_false
                else -> if(badgeList[index] == -1) R.string.badge_info_9_true else R.string.badge_info_9_false
            }).toString()
            dialogExitButton.setOnClickListener { dialog.dismiss() }
        }
        dialog.show()
    }
}