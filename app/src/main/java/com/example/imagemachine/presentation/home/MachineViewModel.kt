package com.example.imagemachine.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.domain.model.GetMachineListUseCase
import com.example.domain.model.MachineItem
import kotlinx.coroutines.launch

class MachineViewModel(private val getMachineListUseCase: GetMachineListUseCase) : ViewModel() {

    private val _machineLiveData = MutableLiveData<Resource<List<MachineItem>>>()
    val machineLiveData: LiveData<Resource<List<MachineItem>>> = _machineLiveData

    init { getMachineList() }

    private fun getMachineList() {
        viewModelScope.launch {
            getMachineListUseCase().collect {
                _machineLiveData.value = it
            }
        }
    }
}