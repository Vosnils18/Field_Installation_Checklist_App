package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.CabinetDao
import eu.foxbms.installationchecklist.data.local.Cabinet
import kotlinx.coroutines.flow.Flow

class CabinetRepository(private val cabinetDao: CabinetDao) {
    suspend fun insertCabinet(cabinet: Cabinet): Long {
        return cabinetDao.insert(cabinet)
    }

    suspend fun updateCabinet(cabinet: Cabinet) {
        cabinetDao.update(cabinet)
    }

    fun getCabinetsForProject(projectId: Long): Flow<List<Cabinet>> {
        return cabinetDao.getCabinetsForProject(projectId)
    }

    fun getCabinetById(cabinetId: Long): Flow<Cabinet?> {
        return cabinetDao.getCabinet(cabinetId)
    }

    suspend fun deleteCabinet(id: Long) {
        cabinetDao.delete(id)
    }
}