package eu.foxbms.installationchecklist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.foxbms.installationchecklist.data.local.AppDatabase
import eu.foxbms.installationchecklist.data.local.CabinetDao
import eu.foxbms.installationchecklist.data.local.FieldDeviceDao
import eu.foxbms.installationchecklist.data.local.PhotoDao
import eu.foxbms.installationchecklist.data.local.ProjectDao
import eu.foxbms.installationchecklist.data.repository.CabinetRepository
import eu.foxbms.installationchecklist.data.repository.FieldDeviceRepository
import eu.foxbms.installationchecklist.data.repository.PhotoRepository
import eu.foxbms.installationchecklist.data.repository.ProjectRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database.db"
        ).build()

    @Provides
    @Singleton
    fun provideProjectDao(database: AppDatabase): ProjectDao = database.projectDao()

    @Provides
    @Singleton
    fun provideCabinetDao(database: AppDatabase): CabinetDao = database.cabinetDao()

    @Provides
    @Singleton
    fun provideFieldDeviceDao(database: AppDatabase): FieldDeviceDao = database.fieldDeviceDao()

    @Provides
    @Singleton
    fun providePhotoDao(database: AppDatabase): PhotoDao = database.photoDao()

    @Provides
    @Singleton
    fun provideProjectRepository(projectDao: ProjectDao): ProjectRepository =
        ProjectRepository(projectDao)

    @Provides
    @Singleton
    fun provideCabinetRepository(cabinetDao: CabinetDao): CabinetRepository =
        CabinetRepository(cabinetDao)

    @Provides
    @Singleton
    fun provideFieldDeviceRepository(fieldDeviceDao: FieldDeviceDao): FieldDeviceRepository =
        FieldDeviceRepository(fieldDeviceDao)

    @Provides
    @Singleton
    fun providePhotoRepository(photoDao: PhotoDao): PhotoRepository =
        PhotoRepository(photoDao)
}
