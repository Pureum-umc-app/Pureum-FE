package kr.co.pureum.views.profile

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentProfileTempBinding
import kr.co.pureum.views.MainActivity

class ProfileTempFragment : BaseFragment<FragmentProfileTempBinding>(R.layout.fragment_profile_temp) {
    lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
    }

    private fun initToolbar() {
        mainActivity = context as MainActivity
        with(mainActivity) {
            supportActionBar?.setDisplayUseLogoEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            binding.mainToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initView() {
        binding.profileTempTextView.text = "프로필에서 넘어온 화면입니다."
        Log.e("ScreenBuild", "ProfileTempFragment")
    }
}