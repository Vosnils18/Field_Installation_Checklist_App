package eu.foxbms.installationchecklist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val entityId: Int,
    val entityType: String,
    val uri: String,
    val createdAt: Long
)
