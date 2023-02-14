package kr.co.pureum.utils

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import ko.co.data.remote.PureumService
import ko.co.data.source.home.HomeDataSource
import ko.co.data.source.home.HomeRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.domain.model.DailyRecord
import kr.co.domain.repository.HomeRepository
import kr.co.pureum.di.PureumApplication
import javax.inject.Inject

class PureumBroadcastReceiver : BroadcastReceiver() {
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface PureumBroadcastReceiverEntryPoint {
        fun repository(): HomeRepository
    }

    private lateinit var context: Context
    private val repository: HomeRepository by lazy {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            PureumBroadcastReceiverEntryPoint::class.java
        ).repository()
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method will be called when the alarm goes off at 12:00am
        // Put your code for the repeating task here
        this.context = context
        val dailyRecord = DailyRecord(
            count = intent.getIntExtra("count", 0),
            hour = (intent.getIntExtra("time", 0) / 60).toString(),
            minute = (intent.getIntExtra("time", 0) % 60).toString(),
        )
        CoroutineScope(Dispatchers.IO).launch {
            repository.commitDailyRecord(PureumApplication.spfManager.getUserId(), dailyRecord)
        }
        with(PureumApplication.spfManager) {
            clearPurposeTime()

        }
    }

    fun test(context: Context, intent: Intent) {
        this.onReceive(context, intent)
    }
}