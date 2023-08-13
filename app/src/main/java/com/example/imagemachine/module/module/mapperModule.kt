package com.example.imagemachine.module.module

import com.example.data.MachineMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MachineMapper() }
}