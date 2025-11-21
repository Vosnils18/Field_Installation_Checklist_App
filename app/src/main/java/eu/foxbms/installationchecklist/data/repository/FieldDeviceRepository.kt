package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.FieldDeviceDao
import eu.foxbms.installationchecklist.data.local.FieldDevice
import kotlinx.coroutines.flow.Flow

// TODO: Change these functions or delete them to conform to the new architecture
class FieldDeviceRepository(private val fieldDeviceDao: FieldDeviceDao) {
    fun getFieldDevicesByCabinetId(cabinetId: Int): Flow<List<FieldDevice>> =
        fieldDeviceDao.getFieldDevicesByCabinetId(cabinetId)

    fun getFieldDeviceById(id: Int): Flow<FieldDevice?> =
        fieldDeviceDao.getFieldDeviceById(id)

    suspend fun insertFieldDevice(fieldDevice: FieldDevice) =
        fieldDeviceDao.insertFieldDevice(fieldDevice)

    suspend fun updateFieldDevice(fieldDevice: FieldDevice) =
        fieldDeviceDao.updateFieldDevice(fieldDevice)

    suspend fun deleteFieldDevice(id: Int) = fieldDeviceDao.deleteFieldDevice(id)
}
