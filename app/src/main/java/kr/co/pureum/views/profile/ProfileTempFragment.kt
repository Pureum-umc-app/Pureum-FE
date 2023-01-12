package kr.co.pureum.views.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentProfileTempBinding

class ProfileTempFragment : BaseFragment<FragmentProfileTempBinding>(R.layout.fragment_profile_temp) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.profileTextView.text = "프로필 화면입니다."
        Log.e("ScreenBuild", "ProfileFragment")
    }
}