package kr.co.pureum.adapter.home

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.pureum.views.home.UsageTimeFragment
import java.time.LocalDate

class UsageTimeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        const val START_POSITION = Int.MAX_VALUE / 2
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getItemId(position: Int): Long {
        val date = LocalDate.now().plusDays((position - START_POSITION).toLong())
        return date.year * 10000L + date.monthValue * 100L + date.dayOfMonth
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createFragment(position: Int): Fragment {
        val itemId = getItemId(position)
        return UsageTimeFragment().apply {
            arguments = Bundle().apply {
                putInt("year" , (itemId / 10000L).toInt())
                putInt("month" , ((itemId % 10000L) / 100L).toInt())
                putInt("day", (itemId % 100L).toInt())
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