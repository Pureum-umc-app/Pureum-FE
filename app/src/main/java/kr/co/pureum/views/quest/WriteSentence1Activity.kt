package kr.co.pureum.views.quest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityWriteSentence1Binding
import kr.co.pureum.views.MainActivity

class WriteSentence1Activity : BaseActivity<ActivityWriteSentence1Binding>(R.layout.activity_write_sentence1) {

    override fun initView() {
        initToolbar()
    }

    private fun initToolbar() {
        val toolbarBodyTemplate = binding.mainToolbar
        setSupportActionBar(toolbarBodyTemplate)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.mainToolbar.setOnClickListener{
            finish()
        }
    }
}
