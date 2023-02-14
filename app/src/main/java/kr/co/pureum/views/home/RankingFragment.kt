package kr.co.pureum.views.home

import android.os.Build
import android.os.Bundle
import android.service.notification.NotificationListenerService.Ranking
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
        with(binding.mainToolbar){
            logo = null
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener { findNavController().navigateUp() }
        }
        mainActivity = activity as MainActivity
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_toolbar, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                when (menuItem.itemId) {
                    R.id.toolbar_calender -> {
                        HomeFragment.showCalendarDialog(requireContext())
                        true
                    }
                    else -> false
                }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        viewModel.getMyGrade()
        viewModel.getAllRankList()
        setDate(RankingViewModel.INIT)
        binding.rankingRecyclerView.apply {
            adapter = RankingAdapter().apply {
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (binding.isLoading == false
                            && (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == itemCount - 1
                            && viewModel.rankListLiveData.value!!.size % 25 == 0) {
                            binding.isLoading = true
                            if (viewModel.isSame) viewModel.getSameRankList()
                            else viewModel.getAllRankList()
                        }
                    }
                })
            }
            layoutManager = LinearLayoutManager(requireContext())
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
                viewModel.switchCategory()
                binding.grade = if(viewModel.isSame) viewModel.gradeLiveData.value else "전체"
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observe() {
        viewModel.gradeLiveData.observe(viewLifecycleOwner) {
            binding.grade = it
        }
        viewModel.rankListLiveData.observe(viewLifecycleOwner) {
            (binding.rankingRecyclerView.adapter as RankingAdapter).setData(it)
            binding.isLoading = false
        }
        viewModel.myRankLiveDate.observe(viewLifecycleOwner) {
            binding.rank = it
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDate(option: Int) {
        with(binding) {
            with(viewModel) {
                setDate(option)
                isLoading = true
                date = localDate
                isToday = isYesterday()
            }
        }
    }
}