package com.example.imagemachine.module.module

import androidx.room.Room
import com.example.imagemachine.MACHINE_DATA_BASE
import com.example.imagemachine.MachineDatabase
import org.koin.dsl.module

/**
 * @author asadurrahman.qayyim
 * @date 13-Aug-2023
 */

val roomModule = module {
    single { Room.databaseBuilder(get(), MachineDatabase::class.java, MACHINE_DATA_BASE).build() }
    single { get<MachineDatabase>().getMachineDao() }
}
