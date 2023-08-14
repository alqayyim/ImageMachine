package com.example.imagemachine.module.module

import com.example.imagemachine.presentation.dashboard.QRScannerViewModel
import com.example.imagemachine.presentation.detail.MachineDetailViewModel
import com.example.imagemachine.presentation.home.MachineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MachineViewModel(get(), get()) }
    viewModel { MachineDetailViewModel(get(), get()) }
    viewModel { QRScannerViewModel(get(), get()) }

}
