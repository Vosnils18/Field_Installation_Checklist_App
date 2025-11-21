package eu.foxbms.installationchecklist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldDeviceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fieldDevice: FieldDevice): Long

    @Update
    suspend fun update(fieldDevice: FieldDevice)

    @Query("SELECT * FROM field_devices WHERE cabinetId = :cabinetId")
    fun getFieldDevicesForCabinet(cabinetId: Long): Flow<List<FieldDevice>>

    @Query("SELECT * FROM field_devices WHERE id = :fieldDeviceId")
    fun getFieldDevice(fieldDeviceId: Long): Flow<FieldDevice?>
}
