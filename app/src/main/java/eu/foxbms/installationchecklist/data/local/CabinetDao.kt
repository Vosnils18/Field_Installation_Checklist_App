package eu.foxbms.installationchecklist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CabinetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cabinet: Cabinet): Long

    @Update
    suspend fun update(cabinet: Cabinet)

    @Query("SELECT * FROM cabinets WHERE projectId = :projectId")
    fun getCabinetsForProject(projectId: Long): Flow<List<Cabinet>>

    @Query("SELECT * FROM cabinets WHERE id = :cabinetId")
    fun getCabinet(cabinetId: Long): Flow<Cabinet?>
}