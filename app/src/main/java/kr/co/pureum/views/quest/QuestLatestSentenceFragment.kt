package kr.co.pureum.views.quest

import android.app.Dialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.pureum.R
import kr.co.pureum.adapter.quest.DataWrittenSentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.DialogSentenceBlameBinding
import kr.co.pureum.databinding.FragmentQuestLatestSentenceBinding

class QuestLatestSentenceFragment : BaseFragment<FragmentQuestLatestSentenceBinding>(R.layout.fragment_quest_latest_sentence) {
    private var wordId: Long = 0
    private val limit = 20
    private var index: Int = 0
    private var page = 0
    private val sort = "date"
    private var lastBlamedSentenceId: Long = -1
    private var lastBlamedState: String? = null

    private lateinit var viewModel: QuestViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initApplyRecyclerView()
        initClickListener()
        observe()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initView() {
        viewModel = (requireParentFragment() as QuestVoidFragment).viewModel
        viewModel.getTodayKeyword()
        wordId = viewModel.keywordIdLiveData.value!!.toLong()
        Log.e(ContentValues.TAG, "$wordId $page $limit $sort")
        viewModel.sentencesList(wordId, page, limit, sort)
        with(binding) {
            isLoading = true
        }
    }

    override fun onPause() {
        super.onPause()
    }
    private fun initClickListener() {
    }

    private fun initApplyRecyclerView() {
        val managerSentence = LinearLayoutManager(activity)
        managerSentence.reverseLayout = true
        managerSentence.stackFromEnd = true
        binding.questLatestSentenceRv.layoutManager = managerSentence
        binding.questLatestSentenceRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.questLatestSentenceRv.adapter = DataWrittenSentenceRVAdapter(object :
            DataWrittenSentenceRVAdapter.OnDataWrittenSentenceClickListener {
            override fun onBlameClickListener(sentenceId: Long, isBlamed :String) {
                showBlameDialog(sentenceId, isBlamed)
            }

            override fun onLikeButtonClickListener(sentenceId: Long) {
                viewModel.postLike(sentenceId, wordId, page, limit, sort)
            }
        })
    }

    private fun observe() {
        viewModel.todayKeywordIdListLiveData.observe(viewLifecycleOwner) {
            wordId = it[index]
            Log.e(ContentValues.TAG, wordId.toString())
        }
        viewModel.sentenceListLiveData.observe(viewLifecycleOwner) {
            Log.e(ContentValues.TAG, "QuestLatestSentenceFragment sentenceListLiveData : $it")
            (binding.questLatestSentenceRv.adapter as DataWrittenSentenceRVAdapter).setData(it!!)
            binding.isLoading = false
        }
        viewModel.blameSentenceLiveData.observe(viewLifecycleOwner) {
            if(it.isSuccess && lastBlamedState != null) {
                val newBlameState = if (lastBlamedState == "T") "F" else "T"
                (binding.questLatestSentenceRv.adapter as DataWrittenSentenceRVAdapter).updateItem(lastBlamedSentenceId, newBlameState)
            } else {
                Log.d(ContentValues.TAG, "blame failed : $it")
            }
        }
    }

    private fun showBlameDialog(sentenceId: Long, isBlamed: String) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogSentenceBlameBinding.inflate(LayoutInflater.from(requireContext()))
        with(dialog) {
            window!!.setBackgroundDrawableResource(R.drawable.bg_rectangle_20dp)
            setContentView(dialogBinding.root)
        }
        with(dialogBinding) {
            dialogBlameTv.text = if (isBlamed == "T") getString(R.string.dialog_sentence_blame_cancel)
            else getString(R.string.dialog_sentence_blame)
            dialogButtonNoBt.setOnClickListener { dialog.dismiss() }
            dialogButtonYesBt.setOnClickListener {
                Log.d("Tag", "sentenceId : $sentenceId")
                lastBlamedSentenceId = sentenceId
                lastBlamedState = isBlamed
                viewModel.blameSentence(sentenceId)
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}