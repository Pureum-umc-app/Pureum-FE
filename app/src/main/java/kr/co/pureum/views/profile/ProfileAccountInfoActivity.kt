package kr.co.pureum.views.profile

import android.content.Intent
import android.view.MenuItem
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityProfileAccountInfoBinding

class ProfileAccountInfoActivity : BaseActivity<ActivityProfileAccountInfoBinding>(R.layout.activity_profile_account_info) {
    override fun initView() {
        initToolbar()
        initListener()
    }

    private fun initListener() {
        with(binding) {
            profileSettingButton.setOnClickListener {
                val intent = Intent(this@ProfileAccountInfoActivity, ProfileSettingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initToolbar() {
        val toolbarBodyTemplate = binding.mainToolbar
        setSupportActionBar(toolbarBodyTemplate)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}