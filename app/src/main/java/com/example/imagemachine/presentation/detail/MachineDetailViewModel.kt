package com.example.imagemachine.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.domain.model.MachineImage
import com.example.domain.model.MachineItem
import com.example.domain.usecase.SaveMachineItemUseCase
import kotlinx.coroutines.launch

class MachineDetailViewModel(
    private val saveMachineItemUseCase: SaveMachineItemUseCase
) : ViewModel() {

    private val _saveMachineLiveData = MutableLiveData<Resource<Unit>>()
    val saveMachineLiveData: LiveData<Resource<Unit>> = _saveMachineLiveData

    val machineImages = MutableLiveData<List<String>>().apply {
        mutableListOf<String>()
    }

    fun saveMachineItem(data: MachineItem) {
        viewModelScope.launch {
            saveMachineItemUseCase(data).collect {
                _saveMachineLiveData.value = it
            }
        }
    }

    fun getImagesList(): List<String>? {
        return machineImages.value?.let { list ->
            list.ifEmpty { null }
        }
    }
}