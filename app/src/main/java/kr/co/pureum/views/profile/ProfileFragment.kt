package kr.co.pureum.views.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentProfileBinding
import kr.co.pureum.views.MainActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initListener()
    }

    private fun initToolbar() {
        mainActivity = context as MainActivity
        with(mainActivity) {
            supportActionBar?.setDisplayUseLogoEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun initView() {
        binding.profileTextView.text = "프로필 화면입니다."
        Log.e("ScreenBuild", "ProfileFragment")
    }

    private fun initListener() {
        with(binding) {
            profileButton.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToProfileTempFragment()
                findNavController().navigate(action)
            }
        }
    }
}