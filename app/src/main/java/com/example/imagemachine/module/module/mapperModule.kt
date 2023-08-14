package com.example.imagemachine.module.module

import com.example.data.mapper.MachineEntityToMachineMapper
import com.example.data.mapper.MachineToMachineEntityMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MachineEntityToMachineMapper() }
    factory { MachineToMachineEntityMapper() }
}