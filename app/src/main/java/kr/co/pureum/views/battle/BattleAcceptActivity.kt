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
        viewModel.getMyWaitBattleInfo(battleId!!)
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
        viewModel.myWaitBattleInfoLiveData.observe(this) {
            binding.myWaitBattle = it
            Log.e("image", it.userImage)
            binding.isLoading = false
            Glide.with(this)
                .load(it.userImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.myProfileImg)
            Glide.with(this)
                .load(it.userImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.myProfile)

            Glide.with(this)
                .load(it.opponentImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.oppProfile)
            Glide.with(this)
                .load(it.opponentImage)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.oppProfileImg)
        }
    }
    // TODO: 대기 중 대결 정보 반환 APi 연결, 라이브 데이터 뿌리기
}