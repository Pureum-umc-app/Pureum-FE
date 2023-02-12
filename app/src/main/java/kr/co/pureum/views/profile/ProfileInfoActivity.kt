package kr.co.pureum.views.profile

import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityProfileInfoBinding
import kr.co.pureum.databinding.DialogDefaultBinding
import kr.co.pureum.di.PureumApplication

@AndroidEntryPoint
class ProfileInfoActivity : BaseActivity<ActivityProfileInfoBinding>(R.layout.activity_profile_info) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun initView() {
        initToolbar()
        initListener()
        observe()
    }

    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener { finish() }
        }
    }

    private fun initListener() {
        with(binding) {
            profileSettingButton.setOnClickListener {
                startActivity(Intent(this@ProfileInfoActivity, ProfileSettingActivity::class.java).apply {
                    putExtra("nickname", intent.getStringExtra("nickname"))
                    putExtra("profileUrl", intent.getStringExtra("profileUrl"))
                })
            }

            profileLogoutButton.setOnClickListener {
                PureumApplication.spfManager.spfClear()
                finishAffinity()
            }
            profileWithdrawalButton.setOnClickListener { showConfirmDialog() }
        }
    }

    private fun observe() {
        viewModel.withdrawalResponseLiveData.observe(this) {
            PureumApplication.spfManager.spfClear()
            finishAffinity()
        }
    }

    private fun showConfirmDialog() {
        val dialog = Dialog(this)
        val dialogBinding = DialogDefaultBinding.inflate(LayoutInflater.from(this))
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_20dp)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding) {
            titleText = "정말 탈퇴하시겠습니까?"
            cancelText = "취소"
            confirmText = "확인"
            dialogCancelButton.setOnClickListener { dialog.dismiss() }
            dialogConfirmButton.setOnClickListener {
                viewModel.withdrawal()
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}