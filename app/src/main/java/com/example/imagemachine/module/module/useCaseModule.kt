package com.example.imagemachine.module.module

import com.example.domain.usecase.GetMachineListUseCase
import com.example.domain.usecase.SaveMachineItemUseCase
import com.example.domain.usecase.SaveMachineListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetMachineListUseCase(get()) }
    single { SaveMachineListUseCase(get()) }
    single { SaveMachineItemUseCase(get()) }
}