package com.example.imagemachine

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.entity.Converters
import com.example.data.entity.MachineDao
import com.example.data.entity.MachineItemEntity
import com.example.domain.model.MachineItem

/**
 * @author asadurrahman.qayyim
 * @date 13-Aug-2023
 */

const val MACHINE_DATA_BASE = "machine-database"

@Database(entities = [MachineItemEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class MachineDatabase : RoomDatabase() {
    abstract fun getMachineDao(): MachineDao
}