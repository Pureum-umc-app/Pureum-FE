package kr.co.pureum.views.chat

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentChatBinding
import kr.co.pureum.views.MainActivity

class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {
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
        binding.chatTextView.text = "채팅 화면입니다."
        Log.e("ScreenBuild", "ChatFragment")
    }
}