package com.example.domain.repository

import com.example.core.data.Resource
import com.example.domain.model.MachineItem
import kotlinx.coroutines.flow.Flow

interface MachineRepository {

    suspend fun getMachineList(): Flow<Resource<List<MachineItem>>>

    suspend fun saveMachineList(request: List<MachineItem>): Flow<Resource<Unit>>

    suspend fun saveMachineItem(request: MachineItem): Flow<Resource<Unit>>
}