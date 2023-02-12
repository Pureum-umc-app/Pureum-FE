package kr.co.pureum.views.quest

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
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
import kr.co.pureum.databinding.ActivityQuestWriteSentenceBinding
import kr.co.pureum.views.MainActivity

@AndroidEntryPoint
class QuestWriteSentenceActivity : BaseActivity<ActivityQuestWriteSentenceBinding>(R.layout.activity_quest_write_sentence) {
    private lateinit var _keyword: String
    private val viewModel by viewModels<QuestViewModel>()
    override fun initView() {
        _keyword = intent.getStringExtra("keyword").toString()
        Log.d(ContentValues.TAG, _keyword)
        observe()
        initToolbar()
        initClickListener()
        viewModel.getSentencesIncomplete()
        with(binding) {
            isLoading = true
            keyword = _keyword
        }
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
    }

    // 뒤로가기 클릭 시 다이얼로그 호출
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backDialog(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initClickListener() {
        binding.questSentenceCompletionBt.setOnClickListener {
            val keyword = binding.questKeywordTv.text.toString()
            val sentence = binding.questSentenceWritingEt.text

            if (sentence.isEmpty() || sentence.length < 10 || !sentence.contains(keyword)) {
                errorDidaLog(this, sentence, keyword)
            }
            else if (sentence.length >= 10 && sentence.contains(keyword)) {
                completionDialog(this, keyword)
            }
        }
        privatePublicButton()
    }

    private fun observe() {
        val index = intent.getIntExtra("index", 1)
        Log.e(ContentValues.TAG, index.toString())
        viewModel.todayKeywordMeaningListLiveData.observe(this) {
            with(binding) {
                definition = it[index]
                isLoading = false
            }
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

    //작성완료 다이얼로그
    private fun completionDialog(context: Context, keyword: String) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_completion_msg)
        dialog.findViewById<TextView>(R.id.dialog_today_keyword_tv).text = keyword
        dialog.findViewById<TextView>(R.id.dialog_public_private_tv).text = binding.questSentencePublicPrivateBt.text.toString()
        val mySentenceButton = dialog.findViewById<Button>(R.id.dialog_go_my_sentence_bt)
        val anotherSentenceButton = dialog.findViewById<Button>(R.id.dialog_go_another_sentence_bt)

        mySentenceButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("screen", 4)
                putExtra("mySentence", 10)
            }
            startActivity(intent)
            finish()
        }
        anotherSentenceButton.setOnClickListener {
            finish()
        }



        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_home_goal_time)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

    // 공개, 비공개 버튼 클릭 시 변경 이벤트
    private fun privatePublicButton() {
        binding.questSentencePublicPrivateBt.setOnClickListener {
            when (binding.questSentencePublicPrivateBt.isSelected) {
                true -> {
                    binding.questSentencePublicPrivateBt.isSelected = false
                    binding.questSentencePublicPrivateBt.text = "비공개"
                    binding.questSentencePublicPrivateBt.setTextColor(Color.parseColor("#85B5FF"))
                }
                false -> {
                    binding.questSentencePublicPrivateBt.isSelected = true
                    binding.questSentencePublicPrivateBt.text = "공개"
                    binding.questSentencePublicPrivateBt.setTextColor(Color.parseColor("#ffffff"))
                }
            }
        }
    }

}