package eu.foxbms.installationchecklist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CabinetDao {
    @Insert
    suspend fun insertCabinet(cabinet: CabinetEntity)

    @Update
    suspend fun updateCabinet(cabinet: CabinetEntity)

    @Query("DELETE FROM cabinets WHERE id = :id")
    suspend fun deleteCabinet(id: Int)

    @Query("SELECT * FROM cabinets WHERE projectId = :projectId")
    fun getCabinetsByProjectId(projectId: Int): Flow<List<CabinetEntity>>

    @Query("SELECT * FROM cabinets WHERE id = :id")
    fun getCabinetById(id: Int): Flow<CabinetEntity?>
}
