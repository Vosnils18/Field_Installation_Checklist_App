package eu.foxbms.installationchecklist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ProjectEntity::class,
        CabinetEntity::class,
        FieldDeviceEntity::class,
        PhotoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun cabinetDao(): CabinetDao
    abstract fun fieldDeviceDao(): FieldDeviceDao
    abstract fun photoDao(): PhotoDao
}
