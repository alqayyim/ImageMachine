package com.example.imagemachine.module.module

import com.example.data.MachineEntityToMachineMapper
import com.example.data.MachineToMachineEntityMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MachineEntityToMachineMapper() }
    factory { MachineToMachineEntityMapper() }
}