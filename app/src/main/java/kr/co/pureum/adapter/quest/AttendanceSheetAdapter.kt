package kr.co.pureum.adapter.quest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.pureum.R
import kr.co.pureum.databinding.ItemAttendanceSheetBinding

class AttendanceSheetAdapter: RecyclerView.Adapter<AttendanceSheetAdapter.ViewHolder>() {
    private lateinit var binding: ItemAttendanceSheetBinding
    private var sheetList = mutableListOf<Int>()

    inner class ViewHolder(val binding: ItemAttendanceSheetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stamps: Int){
            binding.stamps = stamps
        }
    }

    override fun getItemCount(): Int = sheetList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_attendance_sheet, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sheetList[position])
    }

    fun setData(data: List<Int>) {
        sheetList.clear()
        sheetList.addAll(data)
        notifyItemRangeInserted(0, sheetList.size)
    }
}