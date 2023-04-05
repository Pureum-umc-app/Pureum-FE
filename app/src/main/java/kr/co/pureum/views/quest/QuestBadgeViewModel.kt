package kr.co.pureum.views.quest

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.co.domain.model.SaveBadgeRequest
import kr.co.domain.repository.BadgeRepository
import kr.co.pureum.R
import kr.co.pureum.databinding.DialogBadgeAchieveBinding
import kr.co.pureum.di.PureumApplication
import kr.co.pureum.views.MainActivity
import javax.inject.Inject

@HiltViewModel
class QuestBadgeViewModel @Inject constructor(
    private val repository: BadgeRepository
) : ViewModel() {
    private val _badgeListLiveData = MutableLiveData<List<Int>>()
    private val _badgeResultLiveData = MutableLiveData<String>()

    val badgeListLiveData: LiveData<List<Int>> = _badgeListLiveData
    val badgeResultLiveData: LiveData<String> = _badgeResultLiveData

    fun saveBadge(badge: Int) {
        viewModelScope.launch {
            val res = repository.saveBadge(SaveBadgeRequest(badge), PureumApplication.spfManager.getUserId())
            _badgeResultLiveData.value = res.message
        }
    }

    fun getBadges() {
        viewModelScope.launch {
            val res = repository.getBadges(PureumApplication.spfManager.getUserId())
            val tempList = res.badges.map{ it * -1 }.toMutableList()
            tempList[0] = tempList[0] * -1
            _badgeListLiveData.value = tempList
        }
    }

    fun showBadgeAchieveDialog(context: Context, activity: Activity, index: Int) {
        val dialog = Dialog(context)
        val dialogBinding = DialogBadgeAchieveBinding.inflate(LayoutInflater.from(context))
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_20dp)
            setContentView(dialogBinding.root)
        }
        dialog.setContentView(dialogBinding.root)
        with(dialogBinding) {
            badgeIndex = index
            badgeDialogInfoText.text = activity.resources.getText(when(index) {
                1 -> R.string.badge_info_1_true
                2 -> R.string.badge_info_2_true
                3 -> R.string.badge_info_3_true
                4 -> R.string.badge_info_4_true
                5 -> R.string.badge_info_5_true
                6 -> R.string.badge_info_6_true
                7 -> R.string.badge_info_7_true
                8 -> R.string.badge_info_8_true
                else -> R.string.badge_info_9_true
            }).toString()
            dialogExitButton.setOnClickListener { dialog.dismiss() }
            badgeDialogButton.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("screen", 3)
                    putExtra("badge", 10)
                }
                dialog.dismiss()
                activity.startActivity(intent)
                activity.finish()
            }
        }
        dialog.show()
    }
}