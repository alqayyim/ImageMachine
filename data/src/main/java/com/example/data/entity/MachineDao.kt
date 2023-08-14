package com.example.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * @author asadurrahman.qayyim
 * @date 13-Aug-2023
 */

@Dao
interface MachineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(machine: MachineItemEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(machine: List<MachineItemEntity>)


    @Query("DELETE FROM machine_table WHERE id = :arg0")
    fun deleteById(arg0: Int)

    @Query("SELECT * from machine_table")
    fun getAllMachine(): List<MachineItemEntity>

    @Query("SELECT * from machine_table WHERE qrCodeNumber = :arg0")
    fun getMachine(arg0: Int): MachineItemEntity

    @Query("SELECT EXISTS(SELECT * FROM machine_table WHERE qrCodeNumber = :arg0)")
    fun isExist(arg0: Int): Boolean
}