package kr.co.pureum.views.profile

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityProfileSentenceModifyBinding

@AndroidEntryPoint
class ProfileSentenceModifyActivity : BaseActivity<ActivityProfileSentenceModifyBinding>(R.layout.activity_profile_sentence_modify) {
    private var status: String = "P"
    private val viewModel by viewModels<ProfileViewModel>()
    private var sentenceId: Long = 0
    override fun initView() {
        sentenceId = intent.getLongExtra("sentenceId", 0)
        Log.e(ContentValues.TAG, sentenceId.toString())
        initToolbar()
        initClickListener()
        with(binding) {
            isLoading = true
        }
        viewModel.getSentenceInfo(sentenceId)
        observe()
    }

    private fun initClickListener() {
        binding.profileSentenceCompletionBt.setOnClickListener {
            val keyword = binding.keyword
            val sentence = binding.profileSentenceWritingEt.text

            if (sentence.isEmpty() || sentence.length < 10 || !sentence.contains(keyword)) {
                errorDidaLog(this, sentence, keyword)
            }
            else if (sentence.length >= 10 && sentence.contains(keyword)) {
                viewModel.modifyMySentence(sentence.toString(), sentenceId)
                completionDialog(this, keyword)
            }
        }
        privatePublicButton()
    }

    private fun observe() {
        with(binding) {
            viewModel.sentenceInfoKeywordData.observe(this@ProfileSentenceModifyActivity) {
                keyword = it
            }
            viewModel.sentenceInfoMeaningData.observe(this@ProfileSentenceModifyActivity) {
                definition = it
            }
            isLoading = false
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
                backDialog(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                backDialog(this)
            }
        }
        return true
    }

    private fun backDialog(context: Context) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_back_msg)
        val continueButton = dialog.findViewById<Button>(R.id.dialog_continue_bt)
        val backButton = dialog.findViewById<Button>(R.id.dialog_back_bt)
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_back)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        continueButton.setOnClickListener {
            dialog.dismiss()
        }
        backButton.setOnClickListener {
            finish()
        }
        dialog.show()
    }
    //작성완료 다이얼로그
    private fun completionDialog(context: Context, keyword: String) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_completion_msg)
        dialog.findViewById<TextView>(R.id.dialog_today_keyword_tv).text = keyword
        dialog.findViewById<TextView>(R.id.dialog_public_private_tv).text = binding.profileSentencePublicPrivateBt.text.toString()
        dialog.setCancelable(false)
        val mySentenceButton = dialog.findViewById<Button>(R.id.dialog_go_my_sentence_bt)
        val anotherSentenceButton = dialog.findViewById<Button>(R.id.dialog_go_another_sentence_bt)

        mySentenceButton.setOnClickListener {
            finish()
        }
        anotherSentenceButton.setOnClickListener {
            finish()
        }
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_home_goal_time)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

    private fun errorDidaLog(context: Context, sentence: Editable, keyword: String) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_error_msg)
        val okButton = dialog.findViewById<Button>(R.id.dialog_button_ok_bt)
        val errorText = dialog.findViewById<TextView>(R.id.dialog_error_text)
        if (sentence.isEmpty()) {
            errorText.text = "내용을 입력해주세요."
        }
        else if (sentence.length < 10) {
            errorText.text = "10자 이상 입력해주세요."
        }

        else if (!sentence.contains(keyword)){
            errorText.text = "키워드를 포함하여 작성해주세요"
        }

        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        okButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun privatePublicButton() {
        binding.profileSentencePublicPrivateBt.setOnClickListener {
            when (binding.profileSentencePublicPrivateBt.isSelected) {
                true -> {
                    binding.profileSentencePublicPrivateBt.isSelected = false
                    binding.profileSentencePublicPrivateBt.text = "비공개"
                    binding.profileSentencePublicPrivateBt.setTextColor(Color.parseColor("#85B5FF"))
                    status = "P"
                }
                false -> {
                    binding.profileSentencePublicPrivateBt.isSelected = true
                    binding.profileSentencePublicPrivateBt.text = "공개"
                    binding.profileSentencePublicPrivateBt.setTextColor(Color.parseColor("#ffffff"))
                    status = "O"
                }
            }
        }
    }

}