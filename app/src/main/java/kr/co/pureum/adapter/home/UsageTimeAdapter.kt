package kr.co.pureum.adapter.home

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.pureum.views.home.UsageTimeItem
import java.time.LocalDate

class UsageTimeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        const val START_POSITION = Int.MAX_VALUE / 2
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getItemId(position: Int): Long {
        val date = LocalDate.now().plusDays((position - START_POSITION).toLong())
        val itemId = date.year * 100000L + date.monthValue * 1000L + date.dayOfMonth * 10L
        if (date.isEqual(LocalDate.now())) itemId + 1
        return itemId
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createFragment(position: Int): Fragment {
        val itemId = getItemId(position)
        return UsageTimeItem().apply {
            arguments = Bundle().apply {
                putInt("year" , (itemId / 100000L).toInt())
                putInt("month" , ((itemId % 100000L) / 1000L).toInt())
                putInt("day", ((itemId % 1000L) / 10L).toInt())
                putInt("isToday", (itemId % 10L).toInt())
            }
        }
    }

    interface Listener {
        fun onClick(combId: Long)
        fun onDelete(combId: Long)
        fun onPost(combId: Long)
    }

    private lateinit var usageTimeListener : Listener

    fun setListener(listener: Listener){
        usageTimeListener = listener
    }
}