package kr.co.pureum.views.quest

import android.os.Bundle
import android.util.Log
import android.view.View
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestBinding
import kr.co.pureum.views.MainActivity

class QuestFragment : BaseFragment<FragmentQuestBinding>(R.layout.fragment_quest) {
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
        binding.questFragment.text = "퀘스트 화면입니다."
        Log.e("ScreenBuild", "QuestFragment")
    }
}