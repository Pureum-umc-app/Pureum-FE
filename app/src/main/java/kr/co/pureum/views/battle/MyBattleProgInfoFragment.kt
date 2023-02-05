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
import kr.co.pureum.R
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
        (requireActivity() as OnBattleActivity).changeToolbarColor()
        viewModel.getMyBattleProgressInfo()
        with(binding) {

            isLoading = false
            day = 1

        }

    }

    private fun initListener() {
        with(binding) {

        }
    }

    private fun observe() {
    }

}