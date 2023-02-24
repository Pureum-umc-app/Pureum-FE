package kr.co.pureum.views.profile

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.pureum.R
import kr.co.pureum.adapter.profile.DataMySentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentProfileMySentenceBinding

@AndroidEntryPoint
class ProfileMySentenceFragment : BaseFragment<FragmentProfileMySentenceBinding>(R.layout.fragment_profile_my_sentence){
    private val viewModel by viewModels<ProfileViewModel>()
    private var count: Int = 0
    private var countOpen: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMySentencesList()
        initRecyclerView()
        initView()
        initListener()
        observe()
    }
    private fun initView() {
        with(binding) {
            isLoading = true
        }
    }

    private fun initListener() {
        with(binding) {
            profileFinishButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun initRecyclerView() {
        val managerMySentence = LinearLayoutManager(activity)
        managerMySentence.reverseLayout = true
        managerMySentence.stackFromEnd = true
        managerMySentence.isSmoothScrollbarEnabled = true
        binding.profileMySentenceRv.layoutManager = managerMySentence
        binding.profileMySentenceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.profileMySentenceRv.adapter = DataMySentenceRVAdapter(object : DataMySentenceRVAdapter.OptionsMenuClickListener {
            override fun onOptionsMenuClicked(sentenceId: Long) {
                Log.e(ContentValues.TAG, sentenceId.toString())
                preformOptionsMenuClick(sentenceId)
            }
        })
    }

    private fun preformOptionsMenuClick(sentenceId: Long) {
        val popupMenu = PopupMenu(activity, binding.profileSentenceCountTv)
        popupMenu.inflate(R.menu.menu_my_sentences)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.menu_modify -> {
                    Toast.makeText(activity, "item_modify_clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_delete -> {
                    Toast.makeText(activity, "item_delete_clicked", Toast.LENGTH_SHORT).show()
                    activity?.let { deleteDialog(it, sentenceId) }
                }
            }
            false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.show()
    }
    private fun observe() {

        viewModel.mySentenceListLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                (profileMySentenceRv.adapter as DataMySentenceRVAdapter).setData(it)
                viewModel.countLiveData.observe(viewLifecycleOwner) {
                    privateNum = it
                    Log.e(ContentValues.TAG, it.toString())
                    count = it
                }
                viewModel.countOpenLiveData.observe(viewLifecycleOwner) {
                    openNum = it
                    Log.e(ContentValues.TAG, it.toString())
                    countOpen = it
                }
                Log.e(ContentValues.TAG, profileMySentenceRv.adapter?.itemCount.toString())
                isLoading = false
            }
        }
    }
    private fun deleteDialog(context: Context, sentenceId: Long) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_sentence_delete)
        val noButton = dialog.findViewById<Button>(R.id.dialog_button_no_bt)
        val yesButton = dialog.findViewById<Button>(R.id.dialog_button_yes_bt)
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_rectangle_10dp)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        noButton.setOnClickListener {
            dialog.dismiss()
        }
        yesButton.setOnClickListener {
            Toast.makeText(activity, "삭제합니다!", Toast.LENGTH_SHORT).show()
            viewModel.deleteMySentence(sentenceId)
            dialog.dismiss()
        }
        dialog.show()
    }
}