package com.example.domain.usecase

import com.example.core.FlowUseCase
import com.example.core.data.Resource
import com.example.domain.model.MachineItem
import com.example.domain.repository.MachineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMachineUseCase(private val repo: MachineRepository) : FlowUseCase<Int, MachineItem>() {
    override suspend fun execute(parameters: Int?): Flow<Resource<MachineItem>> {
        return parameters?.let { repo.getMachine(parameters) }
            ?: flow { Resource.Error(Throwable("Body request can't be empty")) }
    }
}