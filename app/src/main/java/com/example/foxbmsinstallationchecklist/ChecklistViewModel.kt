package com.example.foxbmsinstallationchecklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChecklistViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InstallationData())
    val uiState: StateFlow<InstallationData> = _uiState.asStateFlow()

    // Project Info
    fun updateProjectName(name: String) {
        _uiState.update { it.copy(projectInfo = it.projectInfo.copy(name = name)) }
    }

    fun updateLocation(location: String) {
        _uiState.update { it.copy(projectInfo = it.projectInfo.copy(location = location)) }
    }

    fun updateContactPerson(contactPerson: String) {
        _uiState.update { it.copy(projectInfo = it.projectInfo.copy(contactPerson = contactPerson)) }
    }

    fun updateContactNumber(contactNumber: String) {
        _uiState.update { it.copy(projectInfo = it.projectInfo.copy(contactNumber = contactNumber)) }
    }

    fun updateEmail(email: String) {
        _uiState.update { it.copy(projectInfo = it.projectInfo.copy(email = email)) }
    }

    // Measurements
    fun updateWidth(width: String) {
        _uiState.update { it.copy(measurements = it.measurements.copy(width = width)) }
    }

    fun updateHeight(height: String) {
        _uiState.update { it.copy(measurements = it.measurements.copy(height = height)) }
    }

    fun updateDepth(depth: String) {
        _uiState.update { it.copy(measurements = it.measurements.copy(depth = depth)) }
    }

    fun updateCabinetType(cabinetType: String) {
        _uiState.update { it.copy(measurements = it.measurements.copy(cabinetType = cabinetType)) }
    }

    fun updateCableEntryPosition(cableEntryPosition: String) {
        _uiState.update { it.copy(measurements = it.measurements.copy(cableEntryPosition = cableEntryPosition)) }
    }

    fun updateNumberOfDoors(numberOfDoors: String) {
        _uiState.update { it.copy(measurements = it.measurements.copy(numberOfDoors = numberOfDoors)) }
    }

    fun updateNumberOfShelves(numberOfShelves: String) {
        _uiState.update { it.copy(measurements = it.measurements.copy(numberOfShelves = numberOfShelves)) }
    }

    // Photos
    fun addPhoto(type: String, uri: String) {
        _uiState.update { it.copy(photos = it.photos + (type to uri)) }
    }
}
