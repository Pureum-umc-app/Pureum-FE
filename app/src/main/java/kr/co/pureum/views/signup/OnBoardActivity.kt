package kr.co.pureum.views.signup

import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.signup.OnBoardVPFragmentAdapter
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityOnBoardBinding
import kr.co.pureum.di.PureumApplication
import kr.co.pureum.views.MainActivity

@AndroidEntryPoint
class OnBoardActivity : BaseActivity<ActivityOnBoardBinding>(R.layout.activity_on_board) {

    override fun initView() {
        // 자동 로그인
        if (PureumApplication.spfManager.checkUserToken()) {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("screen", 1)
            })
            finish()
        }
        setViewPager()
    }

    private fun  setViewPager(){
        // 1) ViewPager2 참조
        val viewPager: ViewPager2 = findViewById(R.id.on_board_viewPager)

        // 2) FragmentStateAdapter 생성 : Fragment 여러개를 ViewPager2에 연결해주는 역할
        val viewpagerFragmentAdapter = OnBoardVPFragmentAdapter(this)

        // 3) ViewPager2의 adapter에 설정
        viewPager.adapter = viewpagerFragmentAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                binding.ciOnBoard.selectDot(p0)
            }
        })

        //init indicator
        binding.ciOnBoard.createDotPanel(4, R.drawable.indicator_dot_off, R.drawable.indicator_dot_on, 0)
    }
}
