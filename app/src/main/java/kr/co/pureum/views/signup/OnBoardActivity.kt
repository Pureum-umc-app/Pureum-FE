package kr.co.pureum.views.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import kr.co.pureum.R
import kr.co.pureum.adapter.signup.OnBoardVPFragmentAdapter
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityOnBoardBinding

class OnBoardActivity : BaseActivity<ActivityOnBoardBinding>(R.layout.activity_on_board) {

    override fun initView() {

        setViewPager()

    }

    fun setViewPager(){
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
