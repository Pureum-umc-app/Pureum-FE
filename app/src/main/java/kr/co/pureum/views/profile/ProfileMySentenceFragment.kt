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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRecyclerView()
        initListener()
        observe()
    }
    private fun initView() {
        viewModel.getMySentencesList()
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
        binding.profileMySentenceRv.adapter = DataMySentenceRVAdapter()
    }
    private fun observe() {
        viewModel.countLiveData.observe(viewLifecycleOwner) {
            binding.getMySentenceRes?.count = it
        }
        viewModel.countOpenLiveData.observe(viewLifecycleOwner) {
            binding.getMySentenceRes?.countOpen = it
        }
        viewModel.mySentenceListLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                (profileMySentenceRv.adapter as DataMySentenceRVAdapter).setData(it)
                isLoading = false
            }
        }
    }
}