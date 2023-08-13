package com.example.imagemachine.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.domain.usecase.GetMachineListUseCase
import com.example.domain.model.MachineItem
import com.example.domain.usecase.SaveMachineListUseCase
import kotlinx.coroutines.launch

class MachineViewModel(private val getMachineListUseCase: GetMachineListUseCase,
                       private val saveMachineListUseCase: SaveMachineListUseCase
) : ViewModel() {

    private val _machineLiveData = MutableLiveData<Resource<List<MachineItem>>>()
    val machineLiveData: LiveData<Resource<List<MachineItem>>> = _machineLiveData

    private val _saveMachineLiveData = MutableLiveData<Resource<Unit>>()
    val saveMachineLiveData: LiveData<Resource<Unit>> = _saveMachineLiveData

    fun getMachineList() {
        viewModelScope.launch {
            getMachineListUseCase().collect {
                _machineLiveData.value = it
            }
        }
    }

    fun saveMachineList(data: List<MachineItem>) {
        viewModelScope.launch {
            saveMachineListUseCase(data).collect {
                _saveMachineLiveData.value = it
            }
        }
    }
}