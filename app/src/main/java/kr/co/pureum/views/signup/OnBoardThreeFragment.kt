package kr.co.pureum.views.signup

import android.os.Bundle
import android.view.View
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentOnBoardThreeBinding
import kr.co.pureum.databinding.FragmentOnBoardTwoBinding

class OnBoardThreeFragment : BaseFragment<FragmentOnBoardThreeBinding>(R.layout.fragment_on_board_three) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
//        binding.profileTextView.text = "프로필 화면입니다."
//        Log.e("ScreenBuild", "ProfileFragment")
    }

    private fun initListener() {
        with(binding) {
//            profileButton.setOnClickListener {
//                val action = kr.co.pureum.views.profile.ProfileFragmentDirections.actionProfileFragmentToProfileTempFragment()
//                findNavController().navigate(action)
//            }
//
//            profileOnboardButton.setOnClickListener {
//                val intent = Intent(activity, kr.co.pureum.views.signup.OnBoardActivity::class.java)
//                startActivity(intent)
//            }
        }
    }
}