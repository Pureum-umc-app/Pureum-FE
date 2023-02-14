package kr.co.pureum.views.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.co.domain.model.DataWrittenSentence
import kr.co.pureum.R
import kr.co.pureum.adapter.profile.DataMySentenceRVAdapter
import kr.co.pureum.base.BaseFragment
import kr.co.pureum.databinding.FragmentProfileMySentenceBinding

@AndroidEntryPoint
class ProfileMySentenceFragment : BaseFragment<FragmentProfileMySentenceBinding>(R.layout.fragment_profile_my_sentence){
    private val viewModel by viewModels<ProfileViewModel>()
    private val dataMyWrittenSentenceList: ArrayList<DataWrittenSentence> = arrayListOf()
    private val dataMySentenceAdapter = DataMySentenceRVAdapter(dataMyWrittenSentenceList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initListener()
    }
    private fun initView() {

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
        binding.profileMySentenceRv.adapter = dataMySentenceAdapter
        dataMyWrittenSentenceList.apply {
            add(DataWrittenSentence("구현", R.drawable.ic_appicon_round, "르미", "방금", "6", "리사이클러뷰 구현에 성공했습니다.", "공개"))
            add(DataWrittenSentence("계기", R.drawable.ic_appicon_round, "르미", "방금", "4", "유엠씨 경험이 좋은 계기가 되었습니다.", "공개"))
        }
    }
    private fun observe() {

    }
}