package kr.co.pureum.views.home

import android.os.Build
import android.os.Bundle
import android.service.notification.NotificationListenerService.Ranking
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.home.RankingAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentRankingBinding
import kr.co.pureum.views.MainActivity

@AndroidEntryPoint
class RankingFragment : BaseFragment<FragmentRankingBinding>(R.layout.fragment_ranking) {
    private lateinit var mainActivity: MainActivity
    private val viewModel by viewModels<RankingViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
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
                setDisplayUseLogoEnabled(false)
                setDisplayHomeAsUpEnabled(true)
            }
            binding.mainToolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            addMenuProvider(object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_toolbar, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                    when (menuItem.itemId) {
                        R.id.toolbar_calender -> {
                            // TODO: 월간 목표 달성 여부 확인 페이지로 이동
                            true
                        }
                        else -> false
                    }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        viewModel.getRankInfo()
        setDate(RankingViewModel.INIT)
        with(binding) {
            rankingRecyclerView.apply {
                adapter = RankingAdapter().apply {
                    addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (isLoading == false) {
                                if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == itemCount - 1) {
                                    isLoading = true
                                    viewModel.getAdditionalRankInfo()
                                }
                            }
                        }
                    })
                }
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initListener() {
        with(binding) {
            rankingLeftButton.setOnClickListener {
                setDate(RankingViewModel.MINUS)
            }
            rankingRightButton.setOnClickListener {
                setDate(RankingViewModel.PLUS)
            }
            rankingTypeButton.setOnClickListener {

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observe() {
        viewModel.prevRankListLiveData.observe(viewLifecycleOwner) {
            (binding.rankingRecyclerView.adapter as RankingAdapter).setData(it)
            binding.isLoading = false
        }
        viewModel.myRankLiveDate.observe(viewLifecycleOwner) {
            binding.userRankDto = it
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDate(option: Int) {
        with(binding) {
            with(viewModel) {
                setDate(option)
                isLoading = true
                getMyRank()
                getRankInfo()
                date = localDate
                isToday = isToday()
            }
        }
    }
}