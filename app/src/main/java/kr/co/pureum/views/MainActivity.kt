package kr.co.pureum.views

import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.PendingIntent
import android.app.usage.EventStats
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.base.BaseActivity
import kr.co.pureum.databinding.ActivityMainBinding
import kr.co.pureum.di.PureumApplication
import kr.co.pureum.utils.PureumBroadcastReceiver
import kr.co.pureum.views.quest.QuestBadgeFragment
import kr.co.pureum.views.quest.QuestBadgeViewModel
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    var totalUsageTime: Int = 0
    var screenCount: Int = 0

    @RequiresApi(Build.VERSION_CODES.S)
    override fun initView() {
        // PureumApplication.spfManager.spfClear()
        getUsageStats()
        initBottomNavigation()
        if (!isToday(PureumApplication.spfManager.getAlarmTime())) startAlarm()
        if (intent.hasExtra("first")) achieveBadge()
    }

    private fun getUsageStats() {
        if (!checkUsageStatsPermission()) {
            Log.e(TAG, "The user may not allow the access to apps usage.")
            showToast("Failed to retrieve app usage statistics. " +
                    "You may need to enable access for this app through " +
                    "Settings > Security > Apps with usage access")
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        } else {
            Log.e(TAG, "The user allowed the access to apps usage.")
            totalUsageTime = (calculateTotalUsageTime(getAppUsageStats()) / 60).toInt()
            screenCount = calculateScreenCount(getAppEventStats())
//            showAppEventStats(getAppEventStats())
            Log.e(TAG, "일일 사용 시간: ${totalUsageTime}, 휴대폰 화면 켠 횟수: $screenCount")
        }
    }

    // The `PACKAGE_USAGE_STATS` permission is a not a runtime permission and hence cannot be
    // requested directly using `ActivityCompat.requestPermissions`. All special permissions
    // are handled by `AppOpsManager`.
    private fun checkUsageStatsPermission() : Boolean {
        val appOpsManager = getSystemService(APP_OPS_SERVICE) as AppOpsManager
        // `AppOpsManager.checkOpNoThrow` is deprecated from Android Q

        val applicationInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getApplicationInfo(
                applicationInfo.packageName,
                PackageManager.ApplicationInfoFlags.of(0)
            )
        } else {
            packageManager.getApplicationInfo(applicationInfo.packageName, 0)
        }

        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOpsManager.unsafeCheckOpNoThrow(
                "android:get_usage_stats",
                applicationInfo.uid, packageName
            )
        } else {
            appOpsManager.checkOpNoThrow(
                "android:get_usage_stats",
                applicationInfo.uid, packageName
            )
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private var navController : NavController? = null
    private fun initBottomNavigation() {
        navController = supportFragmentManager.findFragmentById(R.id.main_frame)?.findNavController()?.apply {
            if (intent.hasExtra("screen")){
                graph = navInflater.inflate(R.navigation.main_navigation_graph).apply {
                    setStartDestination( when(intent.getIntExtra("screen", 0)){
                        1 -> R.id.homeFragment
                        2 -> R.id.battleFragment
                        3 -> R.id.questFragment
                        else -> R.id.profileFragment
                    })
                }
            }
            let { binding.mainNavigationBar.setupWithNavController(it) }
        }
    }

    private fun getAppUsageStats(): MutableList<UsageStats> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val startDate = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
            val start = startDate.toInstant().toEpochMilli()
            val end = startDate.plusDays(1).toInstant().toEpochMilli()
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                val usageStatsManager = getSystemService(USAGE_STATS_SERVICE) as UsageStatsManager
                usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, start, end)
            } else { mutableListOf() }
        } else {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -1)
            Log.e(TAG, cal.toString())
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                val usageStatsManager =
                    getSystemService(USAGE_STATS_SERVICE) as UsageStatsManager
                usageStatsManager.queryUsageStats(
                    UsageStatsManager.INTERVAL_DAILY,
                    cal.timeInMillis,
                    System.currentTimeMillis()
                )
            } else { mutableListOf() }
        }
    }

    private fun showAppUsageStats(usageStats: MutableList<UsageStats>) {
        usageStats.forEach {
            if (it.totalTimeInForeground != 0L) {
                Log.e(TAG, "packageName: ${it.packageName}, lastTimeUsed: ${Date(it.lastTimeUsed)}, " +
                        "totalTimeInForeground: ${it.totalTimeInForeground}, ")
            }
        }
    }

    private fun calculateTotalUsageTime(usageStats: MutableList<UsageStats>): Long {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            var total = 0L
            for (stats in usageStats) {
                if (stats.totalTimeInForeground != 0L) {
                    total += stats.totalTimeInForeground
                }
            }
            val time = TimeUnit.MILLISECONDS.toSeconds(total)
            Log.e(TAG, "총 사용 시간: ${time / 3600}시간 ${(time % 3600) / 60}분 ${time % 60}초")
            time
        } else 3000     // TODO: 낮은 버전을 위한 임시
    }

    private fun getAppEventStats(): MutableList<EventStats> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val seoul = ZoneId.of("Asia/Seoul")
            val defaultZone = ZoneId.systemDefault()
            val date = LocalDate.now()
            val startDate = date.atStartOfDay(defaultZone).withZoneSameInstant(seoul)
            val start = startDate.toInstant().toEpochMilli()
            val end = startDate.plusDays(1).toInstant().toEpochMilli()
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val eventStatsManager = getSystemService(USAGE_STATS_SERVICE) as UsageStatsManager
                eventStatsManager.queryEventStats(UsageStatsManager.INTERVAL_DAILY, start, end)
            } else { mutableListOf() }
        } else {
            val cal = Calendar.getInstance()
            cal.add(Calendar.MONTH, -1)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val eventStatsManager = getSystemService(USAGE_STATS_SERVICE) as UsageStatsManager
                eventStatsManager.queryEventStats(
                    UsageStatsManager.INTERVAL_DAILY,
                    cal.timeInMillis,
                    System.currentTimeMillis()
                )
            } else { mutableListOf() }
        }
    }

    private fun showAppEventStats(usageStats: MutableList<EventStats>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            usageStats.forEach {
                Log.e(TAG, "${it.eventType}, ${it.count}, ${it.totalTime}")
            }
        }
    }

    private fun calculateScreenCount(usageStats: MutableList<EventStats>) : Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                for (usage in usageStats) if (usage.eventType == 16) return usage.count
                return 0
            } else return 0
        } else return 9     // TODO: 낮은 버전을 위한 임시

    }

    private fun showAppEvents() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val seoul = ZoneId.of("Asia/Seoul")
            val defaultZone = ZoneId.systemDefault()
            val date = LocalDate.now()
            val startDate = date.atStartOfDay(defaultZone).withZoneSameInstant(seoul)
            val start = startDate.toInstant().toEpochMilli()
            val end = startDate.plusDays(1).toInstant().toEpochMilli()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val eventStatsManager = getSystemService(USAGE_STATS_SERVICE) as UsageStatsManager
                val event = eventStatsManager.queryEvents(start, end)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                  Log.e(TAG, event.toString())
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun startAlarm() {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this, PureumBroadcastReceiver::class.java).apply {
            putExtra("count", screenCount)
            putExtra("time", totalUsageTime)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            alarmIntent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        // Set the alarm to start at 12:00am
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 58)
            set(Calendar.SECOND, 0)
        }
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
//        alarmManager.cancel(pendingIntent)
//        val triggerTime = System.currentTimeMillis() + 10 * 1000
//        alarmManager.set(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            pendingIntent
//        )
//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            AlarmManager.INTERVAL_DAY,
//            pendingIntent
//        )

        // Store the current time as the last alarm time
        PureumApplication.spfManager.setAlarmTime(calendar.timeInMillis)
    }

    private fun isToday(timeInMillis: Long): Boolean {
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_YEAR)
        calendar.timeInMillis = timeInMillis
        val otherDay = calendar.get(Calendar.DAY_OF_YEAR)
        return today == otherDay
    }

    private val badgeViewModel by viewModels<QuestBadgeViewModel>()
    private fun achieveBadge() {
        badgeViewModel.saveBadge(8)
        observe()
    }

    private fun observe() {
        badgeViewModel.badgeResultLiveData.observe(this) {
            badgeViewModel.showBadgeAchieveDialog(this, this,8)
        }
    }
}