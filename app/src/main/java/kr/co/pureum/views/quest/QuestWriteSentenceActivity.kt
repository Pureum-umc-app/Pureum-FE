package kr.co.pureum.views.quest

import android.app.Dialog
import android.content.Context
import android.text.Editable
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityQuestWriteSentenceBinding


class QuestWriteSentenceActivity : BaseActivity<ActivityQuestWriteSentenceBinding>(R.layout.activity_quest_write_sentence) {
    override fun initView() {
        initToolbar()
        initClickListener()
    }
    // 뒤로가기 버튼 or 스와이프 시 dialog
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                backDialog(this)
            }
        }
        return true
    }

    private fun initToolbar() {
        val toolbarBodyTemplate = binding.mainToolbar
        setSupportActionBar(toolbarBodyTemplate)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.mainToolbar.setOnClickListener {
            backDialog(this)
        }
    }

    private fun initClickListener() {
        binding.questSentenceCompletionBt.setOnClickListener {
            val keyword = binding.questKeywordTv.text.toString()
            val sentence = binding.questSentenceWritingEt.text

            Log.d("tag", keyword)
            Log.d("tag", sentence.toString())
            if (sentence.isEmpty() || sentence.length < 10 || !sentence.contains(keyword)) {
                errorDidaLog(this, sentence, keyword)
            }
            else if (sentence.length >= 10 && sentence.contains(keyword)) {
                completionDialog(this, keyword)
            }
        }

        binding.questSentencePublicPrivateBt.setOnClickListener {

        }
    }

    // 작성완료 클릭 시 dialog
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
    // 뒤로가기 dialog
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

    private fun completionDialog(context: Context, keyword: String) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_completion_msg)
        dialog.findViewById<TextView>(R.id.dialog_today_keyword_tv).text = keyword
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_home_goal_time)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

}