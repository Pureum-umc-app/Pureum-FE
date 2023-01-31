package kr.co.pureum.views.battle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentBattleBinding

class BattleFragment : BaseFragment<FragmentBattleBinding>(R.layout.fragment_battle) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initView()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = ContextCompat.getDrawable(context, R.drawable.ic_pureum_logo)
            navigationIcon = null
        }
    }

    private fun initView() {
        Log.e("ScreenBuild", "BattleFragment")
    }
}