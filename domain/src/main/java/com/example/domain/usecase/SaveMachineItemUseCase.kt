package com.example.domain.usecase

import com.example.core.FlowUseCase
import com.example.core.data.Resource
import com.example.domain.model.MachineItem
import com.example.domain.repository.MachineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveMachineItemUseCase(private val repo: MachineRepository) : FlowUseCase<MachineItem, Unit>() {
    override suspend fun execute(parameters: MachineItem?): Flow<Resource<Unit>> {
        return parameters?.let { repo.saveMachineItem(it) }
            ?: flow { Resource.Error(Throwable("Body request can't be empty")) }
    }
}