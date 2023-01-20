package kr.co.pureum.views.quest

import android.app.Dialog
import android.content.Context
import android.text.Editable
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

    private fun initToolbar() {
        val toolbarBodyTemplate = binding.mainToolbar
        setSupportActionBar(toolbarBodyTemplate)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.mainToolbar.setOnClickListener {
            finish()
        }
    }

    private fun initClickListener() {
        val keyword = binding.questKeywordTv.text.toString()
        val sentence = binding.questSentenceWritingEt.text

        if (sentence.isEmpty() || sentence.length < 10 || sentence.contains(keyword)) {
            binding.questSentenceCompletionBt.setOnClickListener {
                didaLog(this, sentence, keyword)
            }
        }

    }


    private fun didaLog(context: Context, sentence: Editable, keyword: String) {
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

        else if (sentence.contains(keyword)){
            errorText.text = "키워드를 포함하여 작성해주세요"
        }


        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        okButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()





    }
}