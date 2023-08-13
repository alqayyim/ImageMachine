package com.example.domain.usecase

import com.example.core.FlowUseCase
import com.example.core.data.Resource
import com.example.domain.model.MachineItem
import com.example.domain.repository.MachineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveMachineListUseCase(private val repo: MachineRepository) : FlowUseCase<List<MachineItem>, Unit>() {
    override suspend fun execute(parameters: List<MachineItem>?): Flow<Resource<Unit>> {
        return parameters?.let { repo.saveMachineList(it) }
            ?: flow { Resource.Error(Throwable("Body request can't be empty")) }
    }
}