package eu.foxbms.installationchecklist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Insert
    suspend fun insertPhoto(photo: PhotoEntity)

    @Query("DELETE FROM photos WHERE id = :id")
    suspend fun deletePhoto(id: Int)

    @Query("SELECT * FROM photos WHERE entityId = :entityId AND entityType = :entityType")
    fun getPhotosByEntity(entityId: Int, entityType: String): Flow<List<PhotoEntity>>
}
