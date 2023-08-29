package kr.co.pureum.views.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initListener()
        observe()

        if (requireActivity().intent.hasExtra("mySentence")){
            val action = ProfileFragmentDirections.actionProfileFragmentToProfileMySentenceFragment()
            findNavController().navigate(action)
            requireActivity().intent.removeExtra("mySentence")
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("Tag", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Tag", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Tag", "onDestroy")
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
    }

    private fun initView() {
        viewModel.getProfileInfo()
    }

    private fun initListener() {
        with(binding) {
            profileMyBadgeButton.setOnClickListener {
                // QuestFragment를 거쳐 내 Badge 확인 페이지로 이동
                val action = ProfileFragmentDirections.actionProfileFragmentToQuestFragment(toBadge = true)
                findNavController().navigate(action)
            }
            profileMySentenceButton.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToProfileMySentenceFragment()
                findNavController().navigate(action)
            }
            profileMyChallengeButton.setOnClickListener {
                // BattleFragment를 거쳐 내 Challenge 확인 페이지로 이동
                val action = ProfileFragmentDirections.actionProfileFragmentToBattleFragment(toAllBattle = true)
                findNavController().navigate(action)
            }

            profileInfoButton.setOnClickListener {
                startActivity(Intent(requireActivity(), ProfileInfoActivity::class.java).apply {
                    putExtra("nickname", binding.profileInfo!!.nickname)
                    putExtra("profileUrl", binding.profileInfo!!.profileUrl)
                })
            }

            profileInquiryButton.setOnClickListener {
                startActivity(Intent(requireActivity(), InquiryActivity::class.java))
            }
            // TODO: 추가 항목 리스너 구현 필요
        }
    }

    private fun observe() {
        viewModel.profileInfoLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                profileInfo = it
                Glide.with(requireContext())
                    .load(it.profileUrl)
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(profileImage)
            }
        }
    }
}