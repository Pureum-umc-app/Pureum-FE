package kr.co.pureum.views.battle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.battle.WaitingBattleAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentBattleBinding
import kr.co.pureum.views.home.HomeFragmentDirections

@AndroidEntryPoint
class BattleFragment : BaseFragment<FragmentBattleBinding>(R.layout.fragment_battle) {
    private val viewModel by viewModels<BattleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
        initListener()
        observe()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
    }

    private fun initView() {
        Log.e("ScreenBuild", "BattleFragment")
        viewModel.getWaitingBattleInfo()
        with(binding) {
            battleWaitingRecyclerView.apply {
                adapter = WaitingBattleAdapter()
                layoutManager = LinearLayoutManager(requireContext())
                isNestedScrollingEnabled = false;
            }
        }
    }

    private fun initListener() {
        with(binding) {
            battleStartButton.setOnClickListener {
                // TODO: 대결 시작 화면으로 이동
                startActivity(Intent(requireContext(), OnBattleActivity::class.java))
            }
            battleMyBattleButton.setOnClickListener {
                val action = BattleFragmentDirections.actionBattleFragmentToMyBattleFragment()
                findNavController().navigate(action)

            }
            battleAllBattleButton.setOnClickListener {
                // TODO: 전체 대결 화면으로 이동
            }
            battleMoreButton.setOnClickListener {
                // TODO: 대기 중인 대결 전체 보기 화면으로 이동
            }
        }
    }

    private fun observe() {
        viewModel.waitingBattleListLiveData.observe(viewLifecycleOwner) {
            (binding.battleWaitingRecyclerView.adapter as WaitingBattleAdapter).setData(it)
        }
    }
}