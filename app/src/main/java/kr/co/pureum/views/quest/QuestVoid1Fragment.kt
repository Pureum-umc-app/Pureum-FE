package kr.co.pureum.views.quest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentQuestVoid1Binding
import kr.co.pureum.views.MainActivity

class QuestVoid1Fragment : BaseFragment<FragmentQuestVoid1Binding>(R.layout.fragment_quest_void1) {
    lateinit var mainActivity : MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar()
        initClickListener()
    }

    private fun initView() {
        binding.questInformTextTv.text = "작성된 문장이 없습니다.\n가장 먼저 문장을 작성해볼까요?"
        binding.questWritingSentenceBt.text = "문장 작성하러 가기"
    }

    private fun initClickListener() {
        binding.questWritingSentenceBt.setOnClickListener() {
            val intent = Intent(activity, WriteSentence1Activity::class.java)
            startActivity(intent)
        }
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


}