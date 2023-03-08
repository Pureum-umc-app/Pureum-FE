package kr.co.pureum.views.profile

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
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
        with(binding) {
            val managerMySentence = LinearLayoutManager(activity)
            managerMySentence.reverseLayout = true
            managerMySentence.stackFromEnd = true
            managerMySentence.isSmoothScrollbarEnabled = true
            profileMySentenceRv.layoutManager = managerMySentence
            profileMySentenceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            profileMySentenceRv.adapter = activity?.let {
                DataMySentenceRVAdapter(object : DataMySentenceRVAdapter.OptionsMenuClickListener {
                    override fun onOptionsMenuClicked(sentenceId: Long, position: Int) {
                        Log.e(ContentValues.TAG, sentenceId.toString())
                    }

                    override fun modifyClicked(sentenceId: Long) {
                        modifyDialog(activity!!,sentenceId)
                    }

                    override fun deleteClicked(sentenceId: Long) {
                        deleteDialog(activity!!,sentenceId)
                    }
                }, it)
            }
        }
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

    private fun modifyDialog(context: Context, sentenceId: Long) {
        Log.e(ContentValues.TAG, sentenceId.toString())
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_sentence_delete)
        val noButton = dialog.findViewById<Button>(R.id.dialog_button_no_bt)
        val yesButton = dialog.findViewById<Button>(R.id.dialog_button_yes_bt)
        val modifyText = dialog.findViewById<TextView>(R.id.dialog_error_text)
        modifyText.text = "문장을 수정하시겠습니까?"
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_rectangle_10dp)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        noButton.setOnClickListener {
            dialog.dismiss()
        }
        yesButton.setOnClickListener {
            Toast.makeText(activity, "삭제합니다!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, ProfileSentenceModifyActivity::class.java).apply {
                putExtra("sentenceId", sentenceId)
            }
            startActivity(intent)
        }
        dialog.show()
    }
}