package kr.co.pureum.views.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentHomeBinding
import kr.co.pureum.views.MainActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
    }

    private fun initToolbar() {
        mainActivity = context as MainActivity
        with(mainActivity) {
            supportActionBar?.setDisplayUseLogoEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun initView() {
        binding.homeTextView.text = "홈 화면입니다."
        Log.e("ScreenBuild", "HomeFragment")
    }
}