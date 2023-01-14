package kr.co.pureum.views.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kr.co.pureum.R
import kr.co.pureum.adapter.home.UsageTimeAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentHomeBinding
import kr.co.pureum.views.MainActivity
import java.time.LocalDateTime

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var mainActivity: MainActivity

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        binding.homeViewPager.apply {
            adapter = UsageTimeAdapter(this@HomeFragment).apply {
                setListener(object : UsageTimeAdapter.Listener{
                    override fun onClick(combId: Long) {}
                    override fun onDelete(combId: Long) {}
                    override fun onPost(combId: Long) {}
                })
            }
            setCurrentItem(UsageTimeAdapter.START_POSITION, false)

            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
            val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
            val screenWidth = resources.displayMetrics.widthPixels
            val offsetPx = screenWidth - pageMarginPx - pagerWidth

            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
        }
    }
}