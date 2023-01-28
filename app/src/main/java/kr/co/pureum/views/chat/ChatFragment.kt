package kr.co.pureum.views.chat

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentChatBinding
import kr.co.pureum.views.MainActivity

class ChatFragment : BaseFragment<FragmentChatBinding>(R.layout.fragment_chat) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
    }

    private fun initView() {
        binding.chatTextView.text = "채팅 화면입니다."
        Log.e("ScreenBuild", "ChatFragment")
    }
}