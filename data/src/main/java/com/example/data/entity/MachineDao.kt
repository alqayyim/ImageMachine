package com.example.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * @author asadurrahman.qayyim
 * @date 13-Aug-2023
 */

@Dao
interface MachineDao {

    @Insert
    fun insert(note: MachineItemEntity)

    @Update
    fun update(note: MachineItemEntity)

    @Delete
    fun delete(note: MachineItemEntity)

    @Query("select * from machine_table")
    fun getAllMachine(): List<MachineItemEntity>
}