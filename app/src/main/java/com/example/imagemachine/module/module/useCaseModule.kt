package com.example.imagemachine.module.module

import com.example.domain.model.GetMachineListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetMachineListUseCase(get()) }
}