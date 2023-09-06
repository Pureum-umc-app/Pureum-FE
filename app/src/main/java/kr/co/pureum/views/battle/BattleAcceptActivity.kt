package kr.co.pureum.views.battle

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityBattleAcceptBinding

@AndroidEntryPoint
class BattleAcceptActivity : BaseActivity<ActivityBattleAcceptBinding>(R.layout.activity_battle_accept) {
    private val viewModel by viewModels<MyBattleProgInfoViewModel>()
    private var battleId: Long? = null
    override fun initView() {
        battleId = intent.getLongExtra("battleId", 0)

        initListener()
        Log.e("battleId", battleId.toString())
        viewModel.getMyBattleProgMoreInfo(battleId!!)
        observe()
    }

    private fun initListener() {
        with(binding) {
            battleAcceptWirteButton.setOnClickListener {
                val intent = Intent(this@BattleAcceptActivity, MyBattleWriteSentenceActivity::class.java).apply {
                    putExtra("battleId", battleId)
                }
                startActivity(intent)
            }
        }
    }

    private fun observe() {
        viewModel.myBattleProgressListLiveData.observe(this) {
            binding.myBattleWattingMoreDto = it

            Glide.with(binding.myProfile.context)
                .load(it.myImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.myProfile)

            Glide.with(binding.myProfileImg.context)
                .load(it.myImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.myProfileImg)

            Glide.with(binding.oppProfile.context)
                .load(it.oppImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.oppProfile)

            Glide.with(binding.oppProfileImg.context)
                .load(it.oppImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.oppProfileImg)
        }
    }
    // TODO: battleId 받기 및 대기 중 대결 정보 반환 APi 연결, 라이브 데이터 뿌리기
}