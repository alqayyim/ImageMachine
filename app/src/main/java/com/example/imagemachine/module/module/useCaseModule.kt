package com.example.imagemachine.module.module

import com.example.domain.usecase.CheckMachineCodeUseCase
import com.example.domain.usecase.DeleteMachineItemUseCase
import com.example.domain.usecase.GetMachineListUseCase
import com.example.domain.usecase.GetMachineUseCase
import com.example.domain.usecase.SaveMachineItemUseCase
import com.example.domain.usecase.SaveMachineListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetMachineUseCase(get()) }
    single { CheckMachineCodeUseCase(get()) }
    single { GetMachineListUseCase(get()) }
    single { SaveMachineListUseCase(get()) }
    single { SaveMachineItemUseCase(get()) }
    single { DeleteMachineItemUseCase(get()) }
}