package kr.co.pureum.views.home

import android.os.Bundle
import android.view.View
import kr.co.pureum.R
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentUsageTimeBinding

class UsageTimeFragment : BaseFragment<FragmentUsageTimeBinding>(R.layout.fragment_usage_time) {
    companion object {
        var instance: UsageTimeFragment? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}