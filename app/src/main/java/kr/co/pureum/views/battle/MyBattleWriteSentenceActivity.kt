package kr.co.pureum.views.battle

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityMyBattleWriteSentenceBinding
import kr.co.pureum.databinding.DialogBattleSentenceCompletionBinding
import kr.co.pureum.views.MainActivity

@AndroidEntryPoint
class MyBattleWriteSentenceActivity : BaseActivity<ActivityMyBattleWriteSentenceBinding>(R.layout.activity_my_battle_write_sentence) {
    private var battleId: Long? = null
    private val viewModel by viewModels<OnBattleViewModel>()
    override fun initView() {
        battleId = intent.getLongExtra("battleId", 0)
        initListener()
    }

    private fun initListener() {
        with(binding) {
            battleSentenceCompletionBt.setOnClickListener {
                completionDialog(this@MyBattleWriteSentenceActivity)
                viewModel.writeSentence(battleId!!, battleSentenceWritingEt.text.toString())
            }
        }
    }

    // TODO:  키워드 의미 api 마무리

    private fun completionDialog(context: Context) {
        val dialogBinding: DialogBattleSentenceCompletionBinding = DialogBattleSentenceCompletionBinding.inflate(layoutInflater)
        val dialog = Dialog(context)
        dialog.setContentView(dialogBinding.root)

        with(dialogBinding) {
            battleGoMyBattle.setOnClickListener {
                val intent = Intent(this@MyBattleWriteSentenceActivity, MainActivity::class.java).apply {
                    putExtra("screen", 2)
                    putExtra("myChallenge", true)
                }
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

            battleExit.setOnClickListener {
                val intent = Intent(this@MyBattleWriteSentenceActivity, MainActivity::class.java).apply {
                    putExtra("screen", 2)
                }
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

    private fun observe() {

    }
}