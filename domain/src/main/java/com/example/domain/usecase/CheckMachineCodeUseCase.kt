package com.example.domain.usecase

import com.example.core.FlowUseCase
import com.example.core.data.Resource
import com.example.domain.repository.MachineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckMachineCodeUseCase(private val repo: MachineRepository) : FlowUseCase<String, Boolean>() {
    override suspend fun execute(parameters: String?): Flow<Resource<Boolean>> {
        return parameters?.let { repo.checkMachine(parameters) }
            ?: flow { Resource.Error(Throwable("Body request can't be empty")) }
    }
}