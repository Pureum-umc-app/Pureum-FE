package kr.co.pureum.views.signup

import android.app.Dialog
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import kr.co.pureum.databinding.DialogAccessRightsPhotoBinding

class AccessDialog (private val context : AppCompatActivity) {

    private lateinit var binding : DialogAccessRightsPhotoBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감
    private lateinit var listener : DialogOKClickedListener

    fun show() {
        binding = DialogAccessRightsPhotoBinding.inflate(context.layoutInflater)

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함


//        binding.content.text = content //부모 액티비티에서 전달 받은 텍스트 세팅

        //ok 버튼 동작
        binding.accessDialogOkBt.setOnClickListener {

            dlg.dismiss()
        }

        //cancel 버튼 동작
//        binding.cancel.setOnClickListener {
//            dlg.dismiss()
//        }

        dlg.show()
    }

    interface DialogOKClickedListener {
        fun onOKClicked(content : String)
    }


}