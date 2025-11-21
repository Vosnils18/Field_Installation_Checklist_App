package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.data.local.CabinetDao
import eu.foxbms.installationchecklist.data.local.FieldDeviceDao
import eu.foxbms.installationchecklist.data.local.FieldDevice
import kotlinx.coroutines.flow.Flow

class FieldDeviceRepository(private val fieldDeviceDao: FieldDeviceDao) {
    suspend fun insertFieldDevice(fieldDevice: FieldDevice): Long {
        return fieldDeviceDao.insert(fieldDevice)
    }

    suspend fun updateFieldDevice(fieldDevice: FieldDevice) {
        fieldDeviceDao.update(fieldDevice)
    }

    fun getFieldDevicesForCabinet(projectId: Long): Flow<List<FieldDevice>> {
        return fieldDeviceDao.getFieldDevicesForCabinet(projectId)
    }

    fun getFieldDevice(fieldDeviceId: Long): Flow<FieldDevice?> {
        return fieldDeviceDao.getFieldDevice(fieldDeviceId)
    }
}
