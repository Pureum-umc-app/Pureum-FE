package kr.co.pureum.views.profile

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import coil.transform.RoundedCornersTransformation
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityProfileSettingBinding
import kr.co.pureum.views.signup.AccessDialog

class ProfileSettingActivity : BaseActivity<ActivityProfileSettingBinding>(R.layout.activity_profile_setting) {
    var agree = 0
    override fun initView() {
        initListener()
        profileImgToAlbum()
    }

    private fun initListener() {
        with(binding) {
            profileSettingCompletionBt.setOnClickListener {
                finish()
            }
            profileSettingBackButton.setOnClickListener {
                onBackPressed()
            }

        }
    }

    private fun profileImgToAlbum(){

        with(binding) {

            val permissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
                result.forEach {
                    if(!it.value) {
//                            Toast.makeText(applicationContext, "권한 동의 필요!", Toast.LENGTH_SHORT).show()
//                        finish()
                    }
                }
            }
            val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                profileImageSetBt.load(uri){
                    transformations(RoundedCornersTransformation(10F,10F,10F,10F))
                }
            }

            checkPermission.launch(permissionList)

            // 앨범 버튼 클릭 리스너 구현
//            profileImageSetBt.setOnClickListener{
//
//                if(agree == 0){
//                    val dlg = AccessDialog(this@ProfileSettingActivity)
//                    dlg.show()
//                    agree++
//                }
//                else{
//                    readImage.launch("image/*")
//                }
//            }
        }
    }
}