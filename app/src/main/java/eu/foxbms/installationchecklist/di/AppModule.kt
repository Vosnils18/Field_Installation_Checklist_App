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
import eu.foxbms.installationchecklist.data.remote.FoxBmsApiService
import eu.foxbms.installationchecklist.data.repository.CabinetRepository
import eu.foxbms.installationchecklist.data.repository.FieldDeviceRepository
import eu.foxbms.installationchecklist.data.repository.PhotoRepository
import eu.foxbms.installationchecklist.data.repository.ProjectRepository
import eu.foxbms.installationchecklist.data.sync.SyncService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.188/foxbms_api/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFoxBmsApiService(retrofit: Retrofit): FoxBmsApiService {
        return retrofit.create(FoxBmsApiService::class.java)
    }

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
    fun provideProjectRepository(projectDao: ProjectDao, syncService: SyncService): ProjectRepository =
        ProjectRepository(projectDao, syncService)

    @Provides
    @Singleton
    fun provideCabinetRepository(cabinetDao: CabinetDao, syncService: SyncService): CabinetRepository =
        CabinetRepository(cabinetDao, syncService)

    @Provides
    @Singleton
    fun provideFieldDeviceRepository(fieldDeviceDao: FieldDeviceDao): FieldDeviceRepository =
        FieldDeviceRepository(fieldDeviceDao)

    @Provides
    @Singleton
    fun providePhotoRepository(photoDao: PhotoDao, syncService: SyncService): PhotoRepository =
        PhotoRepository(photoDao, syncService)

    @Provides
    @Singleton
    fun provideSyncService(apiService: FoxBmsApiService, @ApplicationContext context: Context): SyncService =
        SyncService(apiService, context)
}