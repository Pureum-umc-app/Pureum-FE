package kr.co.pureum.views.battle

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import kr.co.pureum.databinding.DialogBattleSentenceCompletionBinding

class BattleDialog(private val context: AppCompatActivity) {
    private lateinit var binding: DialogBattleSentenceCompletionBinding
    private val dlg = Dialog(context)
    private lateinit var listener: MyDialogClickListener

    fun show() {
        dlg.setContentView(binding.root)
        dlg.setCancelable(false)

        with(binding) {
            battleGoMyBattle.setOnClickListener {
                // TODO: 내 대결 화면 이동 코드 작성

                dlg.dismiss()
            }

            battleExit.setOnClickListener {
                dlg.dismiss()
            }
        }

        dlg.show()
    }

    fun setOnClickedListener(listener: (String) -> Unit) {
        this.listener = object: MyDialogClickListener {
            override fun onClicked() {
                TODO("Not yet implemented")
            }

        }
    }

    interface MyDialogClickListener {
        fun onClicked()
    }
}