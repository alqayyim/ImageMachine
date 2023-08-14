package com.example.imagemachine.presentation.qrcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Resource
import com.example.domain.model.MachineItem
import com.example.domain.usecase.CheckMachineCodeUseCase
import com.example.domain.usecase.GetMachineUseCase
import kotlinx.coroutines.launch

class QRScannerViewModel(
    private val checkMachineCodeUseCase: CheckMachineCodeUseCase,
    private val getMachineCodeUseCase: GetMachineUseCase
) : ViewModel() {


    private val _checkResultLiveData = MutableLiveData<Resource<Boolean>>()
    val checkResultLiveData: LiveData<Resource<Boolean>> = _checkResultLiveData

    private val _machineLiveData = MutableLiveData<Resource<MachineItem>>()
    val machineLiveData: LiveData<Resource<MachineItem>> = _machineLiveData
    fun checkQRCodeResult(result: String) {
        viewModelScope.launch {
            checkMachineCodeUseCase(result).collect {
                when (it) {
                    is Resource.Success -> if (it.data == true) getMachine(result.toInt())
                    else -> _checkResultLiveData.value = it
                }
            }
        }
    }

    private fun getMachine(code: Int) {
        viewModelScope.launch {
            getMachineCodeUseCase(code).collect {
                _machineLiveData.value = it
            }
        }
    }
}