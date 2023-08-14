package com.example.imagemachine.module.module

import com.example.data.repository.MachineRepositoryImpl
import com.example.domain.repository.MachineRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MachineRepository> {
        MachineRepositoryImpl(get(), get(), get())
    }
}