package kr.co.pureum.views.profile

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentProfileBinding
import kr.co.pureum.views.MainActivity
import kr.co.pureum.views.signup.OnBoardActivity

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private lateinit var mainActivity: MainActivity
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initListener()
        observe()
    }

    private fun initToolbar() {
        mainActivity = activity as MainActivity
        with(mainActivity) {
            with(supportActionBar!!) {
                setDisplayUseLogoEnabled(true)
                setDisplayHomeAsUpEnabled(false)
            }
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

            profileOnboardButton.setOnClickListener {
                val intent = Intent(activity, OnBoardActivity::class.java)
                startActivity(intent)
            }

            profileApiButton.setOnClickListener {
                viewModel.nicknameValidation("nickname")
            }
        }
    }

    private fun observe() {
        viewModel.responseMessage.observe(viewLifecycleOwner) {
            Log.e(TAG, it)
        }
    }
}