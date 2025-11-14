package com.example.foxbmsinstallationchecklist.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foxbmsinstallationchecklist.InstallationData
import com.example.foxbmsinstallationchecklist.database.MariaDBHelper
import com.example.foxbmsinstallationchecklist.utils.SecureCredentialsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProjectInfo(
    val name: String = "",
    val location: String = "",
    val contactPerson: String = "",
    val contactNumber: String = "",
    val email: String = ""
)

data class Measurements(
    val width: String = "",
    val height: String = "",
    val depth: String = "",
    val cabinetType: String = "",
    val cableEntryPosition: String = "",
    val numberOfDoors: String = "",
    val numberOfShelves: String = ""
)

data class InstallationData(
    val projectInfo: ProjectInfo = ProjectInfo(),
    val measurements: Measurements = Measurements(),
    val photos: Map<String, String> = emptyMap()
)

class ChecklistViewModel(private val context: Context) : ViewModel() {
    private val credentialsManager = SecureCredentialsManager(context)
    private val _uiState = MutableStateFlow(InstallationData())
    val uiState: StateFlow<InstallationData> = _uiState.asStateFlow()

    fun hasCredentials(): Boolean = credentialsManager.hasCredentials()

    fun getDatabaseHelper(): MariaDBHelper? {
        if (!hasCredentials()) return null

        return MariaDBHelper(
            host = credentialsManager.getHost(),
            port = credentialsManager.getPort(),
            databaseName = credentialsManager.getDatabaseName(),
            username = credentialsManager.getUsername(),
            password = credentialsManager.getPassword()
        )
    }

    fun saveCredentials(host: String, port: Int, dbName: String, username: String, password: String) {
        credentialsManager.saveCredentials(host, port, dbName, username, password)
    }

    fun submitData(onComplete: (Boolean) -> Unit) {
        if (!hasCredentials()) {
            onComplete(false)
            return
        }

        viewModelScope.launch {
            val dbHelper = getDatabaseHelper()
            if (dbHelper == null) {
                onComplete(false)
                return@launch
            }

            try {
                val connected = dbHelper.connect()
                if (!connected) {
                    onComplete(false)
                    return@launch
                }

                val success = dbHelper.insertInstallationData(uiState.value)
                dbHelper.disconnect()
                onComplete(success)
            } catch (e: Exception) {
                e.printStackTrace()
                onComplete(false)
            }
        }
    }

    // Project Info Updates
    fun updateProjectName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                projectInfo = currentState.projectInfo.copy(name = name)
            )
        }
    }
    fun updateLocation(location: String) {
        _uiState.update { currentState ->
            currentState.copy(
                projectInfo = currentState.projectInfo.copy(location = location)
            )
        }
    }
    fun updateContactPerson(contactPerson: String) {
        _uiState.update { currentState ->
            currentState.copy(
                projectInfo = currentState.projectInfo.copy(contactPerson = contactPerson)
            )
        }
    }
    fun updateContactNumber(contactNumber: String) {
        _uiState.update { currentState ->
            currentState.copy(
                projectInfo = currentState.projectInfo.copy(contactNumber = contactNumber)
            )
        }
    }
    fun updateEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(
                projectInfo = currentState.projectInfo.copy(email = email)
            )
        }
    }
    // Measurements Updates
    fun updateWidth(width: String) {
        _uiState.update { currentState ->
            currentState.copy(
                measurements = currentState.measurements.copy(width = width)
            )
        }
    }
    fun updateHeight(height: String) {
        _uiState.update { currentState ->
            currentState.copy(
                measurements = currentState.measurements.copy(height = height)
            )
        }
    }
    fun updateDepth(depth: String) {
        _uiState.update { currentState ->
            currentState.copy(
                measurements = currentState.measurements.copy(depth = depth)
            )
        }
    }
    fun updateCabinetType(cabinetType: String) {
        _uiState.update { currentState ->
            currentState.copy(
                measurements = currentState.measurements.copy(cabinetType = cabinetType)
            )
        }
    }
    fun updateCableEntryPosition(position: String) {
        _uiState.update { currentState ->
            currentState.copy(
                measurements = currentState.measurements.copy(cableEntryPosition = position)
            )
        }
    }
    fun updateNumberOfDoors(doors: String) {
        _uiState.update { currentState ->
            currentState.copy(
                measurements = currentState.measurements.copy(numberOfDoors = doors)
            )
        }
    }
    fun updateNumberOfShelves(shelves: String) {
        _uiState.update { currentState ->
            currentState.copy(
                measurements = currentState.measurements.copy(numberOfShelves = shelves)
            )
        }
    }
    // Photo Updates
    fun addPhoto(type: String, uri: String) {
        _uiState.update { currentState ->
            val newPhotos = currentState.photos.toMutableMap()
            newPhotos[type] = uri
            currentState.copy(photos = newPhotos)
        }
    }
    fun resetForm() {
        _uiState.value = InstallationData()
    }
}