package eu.foxbms.installationchecklist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldDeviceDao {
    @Insert
    suspend fun insertFieldDevice(fieldDevice: FieldDeviceEntity)

    @Update
    suspend fun updateFieldDevice(fieldDevice: FieldDeviceEntity)

    @Query("DELETE FROM field_devices WHERE id = :id")
    suspend fun deleteFieldDevice(id: Int)

    @Query("SELECT * FROM field_devices WHERE cabinetId = :cabinetId")
    fun getFieldDevicesByCabinetId(cabinetId: Int): Flow<List<FieldDeviceEntity>>

    @Query("SELECT * FROM field_devices WHERE id = :id")
    fun getFieldDeviceById(id: Int): Flow<FieldDeviceEntity?>
}
