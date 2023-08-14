package com.example.imagemachine.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagemachine.utils.delegate.viewBinding
import com.example.core.data.Resource
import com.example.core.navigateTo
import com.example.imagemachine.R
import com.example.imagemachine.databinding.FragmentMachineBinding
import com.example.imagemachine.presentation.home.adapter.MachineAdapter
import com.example.imagemachine.utils.HorizontalSpaceItemDecoration
import com.example.imagemachine.utils.VerticalSpaceItemDecoration
import com.example.core.observeData
import com.example.core.px
import com.example.core.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MachineFragment : Fragment(R.layout.fragment_machine) {

    private val binding by viewBinding(FragmentMachineBinding::bind)
    private val viewModel: MachineViewModel by viewModel()
    private val machineAdapter by lazy {
        MachineAdapter(
            onClick = {
                navigateTo(MachineFragmentDirections.actionToDetail(it))
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMachineAdapter()
        observeMachineList()
        viewModel.getMachineList()
        binding.tvSortBy.setOnClickListener {
            BottomDialogSortBy(requireContext(), onClick = {
                when(it) {
                    SortMode.SORT_BY_NAME -> {
                        val data = ArrayList(machineAdapter.currentList).sortedBy { it.machineName }
                        machineAdapter.submitList(data)
                    }
                    SortMode.SORT_BY_TYPE -> {
                        val data = ArrayList(machineAdapter.currentList).sortedBy { it.machineType }
                        machineAdapter.submitList(data)
                    }
                }
            }).showDialog()
        }
    }

    private fun setupMachineAdapter() {
        binding.rvMachine.run {
            addItemDecoration(HorizontalSpaceItemDecoration(6.px))
            addItemDecoration(VerticalSpaceItemDecoration(2.px))
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = machineAdapter
        }
    }

    private fun observeMachineList() {
        observeData(viewModel.machineLiveData) { result ->
            result?.let {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { data ->
                            viewModel.saveMachineList(data)
                            machineAdapter.submitList(data)
                        }
                    }
                    is Resource.Error -> toast("${it.error.message}")
                    else -> {}
                }
            }
        }
    }
}