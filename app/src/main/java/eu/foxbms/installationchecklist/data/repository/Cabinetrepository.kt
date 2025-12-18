package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.CabinetDao
import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.data.sync.SyncService
import kotlinx.coroutines.flow.Flow

class CabinetRepository(
    private val cabinetDao: CabinetDao,
    private val syncService: SyncService
) {
    fun getCabinetsForProject(projectId: Long): Flow<List<Cabinet>> =
        cabinetDao.getCabinetsForProject(projectId)

    fun getCabinetById(cabinetId: Long): Flow<Cabinet?> =
        cabinetDao.getCabinet(cabinetId)

    suspend fun insertCabinet(cabinet: Cabinet): Long {
        val id = cabinetDao.insert(cabinet)
        syncService.syncCabinet(cabinet.copy(id = id))
        return id
    }

    suspend fun updateCabinet(cabinet: Cabinet) {
        cabinetDao.update(cabinet)
        syncService.syncCabinet(cabinet)
    }

    suspend fun deleteCabinet(id: Long) {
        cabinetDao.delete(id)
    }
}