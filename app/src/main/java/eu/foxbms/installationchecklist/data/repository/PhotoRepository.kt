package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.PhotoDao
import eu.foxbms.installationchecklist.data.local.PhotoEntity
import eu.foxbms.installationchecklist.data.sync.SyncService
import kotlinx.coroutines.flow.Flow

class PhotoRepository(
    private val photoDao: PhotoDao,
    private val syncService: SyncService
) {
    fun getPhotosByEntity(entityId: Int, entityType: String): Flow<List<PhotoEntity>> =
        photoDao.getPhotosByEntity(entityId, entityType)

    fun getAllPhotosByEntityId(entityId: Int): Flow<List<PhotoEntity>> =
        photoDao.getAllPhotosByEntityId(entityId)

    suspend fun insertPhoto(photo: PhotoEntity) {
        photoDao.insertPhoto(photo)
        syncService.syncPhoto(photo)
    }

    suspend fun deletePhoto(id: Int) {
        photoDao.deletePhoto(id)
    }
}