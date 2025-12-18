package eu.foxbms.installationchecklist.data.remote

import eu.foxbms.installationchecklist.data.remote.dto.CabinetDto
import eu.foxbms.installationchecklist.data.remote.dto.PhotoDto
import eu.foxbms.installationchecklist.data.remote.dto.ProjectDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface FoxBmsApiService {

    @GET("projects.php")
    suspend fun getAllProjects(): Response<List<ProjectDto>>

    @GET("projects.php")
    suspend fun getProject(@Query("id") id: Long): Response<ProjectDto>

    @POST("projects.php")
    suspend fun createProject(@Body project: ProjectDto): Response<Map<String, Long>>

    @PUT("projects.php")
    suspend fun updateProject(@Body project: ProjectDto): Response<Map<String, Boolean>>

    @DELETE("projects.php")
    suspend fun deleteProject(@Query("id") id: Long): Response<Map<String, Boolean>>

    @GET("cabinets.php")
    suspend fun getCabinetsForProject(@Query("project_id") projectId: Long): Response<List<CabinetDto>>

    @GET("cabinets.php")
    suspend fun getCabinet(@Query("id") id: Long): Response<CabinetDto>

    @POST("cabinets.php")
    suspend fun createCabinet(@Body cabinet: CabinetDto): Response<Map<String, Long>>

    @PUT("cabinets.php")
    suspend fun updateCabinet(@Body cabinet: CabinetDto): Response<Map<String, Boolean>>

    @DELETE("cabinets.php")
    suspend fun deleteCabinet(@Query("id") id: Long): Response<Map<String, Boolean>>

    @GET("photos.php")
    suspend fun getPhotosForEntity(@Query("entity_id") entityId: Int): Response<List<PhotoDto>>

    @POST("photos.php")
    suspend fun uploadPhoto(@Body photo: PhotoDto): Response<Map<String, Long>>

    @DELETE("photos.php")
    suspend fun deletePhoto(@Query("id") id: Int): Response<Map<String, Boolean>>
}