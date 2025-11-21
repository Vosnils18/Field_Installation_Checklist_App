package eu.foxbms.installationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.foxbms.installationchecklist.data.local.FieldDevice
import eu.foxbms.installationchecklist.data.repository.FieldDeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FieldDeviceViewModel(private val fieldDeviceRepository: FieldDeviceRepository) : ViewModel() {
    fun insertFieldDevice(fieldDevice: FieldDevice) {
        viewModelScope.launch {
            fieldDeviceRepository.insertFieldDevice(fieldDevice)
        }
    }

    fun updateFieldDevice(fieldDevice: FieldDevice) {
        viewModelScope.launch {
            fieldDeviceRepository.updateFieldDevice(fieldDevice)
        }
    }

    fun getFieldDevicesForProject(cabinetId: Long): Flow<List<FieldDevice>> {
        return fieldDeviceRepository.getFieldDevicesForCabinet(cabinetId)
    }

    fun getFieldDevice(fieldDeviceId: Long): Flow<FieldDevice?> {
        return fieldDeviceRepository.getFieldDevice(fieldDeviceId)
    }
}
