package kr.co.pureum.views.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import kr.co.domain.model.ContactRequest
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityInquiryBinding

@AndroidEntryPoint
class InquiryActivity : BaseActivity<ActivityInquiryBinding>(R.layout.activity_inquiry) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun initView() {
        binding.inquirySendButton.isEnabled = false
        initToolbar()
        enableNextButton()
        initClickListener()
        observe()
    }
    private fun initToolbar() {
        with(binding.mainToolbar){
            logo = null
            navigationIcon = ContextCompat.getDrawable(context, R.drawable.ic_back)
            setNavigationOnClickListener { finish() }
        }
    }
    private fun initClickListener() {
        binding.inquirySendButton.setOnClickListener {
            val content = binding.inquiryDescEt.text.toString()
            val email  = binding.inquiryEmailEt.text.toString()
            Log.d("Tag", "$content, $email")
            viewModel.postContact(ContactRequest(content, email))
        }
    }
    private fun enableNextButton() {
        with(binding) {
            inquiryEmailEt.addTextChangedListener(object:TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if(!checkEmailPattern(inquiryEmailEt.text.toString())){
                        inquirySendButton.isEnabled = false
                    } else {
                        inquirySendButton.isEnabled = inquiryClauseAgreeCb.isChecked
                                && inquiryDescEt.length() != 0
                    }
                }
            })
            inquiryDescEt.addTextChangedListener(object:TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if(inquiryDescEt.length() == 0){
                        inquirySendButton.isEnabled = false
                    } else {
                        inquirySendButton.isEnabled = inquiryClauseAgreeCb.isChecked
                                && checkEmailPattern(inquiryEmailEt.text.toString())
                    }
                }
            })
            inquiryClauseAgreeCb.setOnClickListener {
                inquirySendButton.isEnabled = inquiryClauseAgreeCb.isChecked
                        && checkEmailPattern(inquiryEmailEt.text.toString()) && inquiryDescEt.length() != 0
            }
            inquiryClauseAgreeTv.setOnClickListener {
                inquiryClauseAgreeCb.isChecked = !inquiryClauseAgreeCb.isChecked
                inquirySendButton.isEnabled = inquiryClauseAgreeCb.isChecked
                        && checkEmailPattern(inquiryEmailEt.text.toString()) && inquiryDescEt.length() != 0
            }
        }
    }
    private fun observe() {
        viewModel.contactLiveData.observe(this) {
            if(it.isSuccess) {
                finish()
            } else {
                Log.d("inquiryActivity", "contact failed : $it")
            }
            showToast("${it.message}")
        }
    }
    private fun checkEmailPattern(email : String) : Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}