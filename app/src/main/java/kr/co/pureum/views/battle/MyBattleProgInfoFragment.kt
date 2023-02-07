package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.domain.model.MyBattleProgMoreDto
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.MyBattleCompletionAdapter
import kr.co.pureum.adapter.battle.MyBattleProgressAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentMyBattleProgInfoBinding
import kr.co.pureum.databinding.FragmentMyBattleProgressBinding
import kr.co.pureum.views.MainActivity
import kr.co.pureum.views.home.HomeFragment

@AndroidEntryPoint
class MyBattleProgInfoFragment : BaseFragment<FragmentMyBattleProgInfoBinding>(R.layout.fragment_my_battle_prog_info) {

    private val viewModel by viewModels<MyBattleProgInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        observe()
    }

    private fun initView() {
        Log.e("ScreenBuild", "MyBattleProgInfoFragment")
        viewModel.getMyBattleProgressInfo()
        with(binding) {
//            isLoading = false
//            day = 3
//            nickname = "푸름"


        }

    }

    private fun initListener() {
        with(binding) {

        }
    }

    private fun observe() {
        viewModel.myBattleProgressListLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                isLoading = false
                keyword = it.keyword.toString()
                nickname = it.nickname.toString()
                mySentence = it.mySentence.toString()
                opponentNickname = it.opponentNickname.toString()
                day = it.day.toInt()
                opponentSentence = it.opponentSentence.toString()
                mySentenceLikeNum = it.mySentenceLikeNum
                opSentenceLikeNum = it.opSentenceLikeNum.toInt()
                mySentenceLike = it.mySentenceLike
                opSentenceLike = it.opSentenceLike
        }

        }
    }

}