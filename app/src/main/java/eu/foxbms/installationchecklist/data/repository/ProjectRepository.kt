package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.ProjectDao
import eu.foxbms.installationchecklist.data.local.Project
import eu.foxbms.installationchecklist.data.sync.SyncService
import kotlinx.coroutines.flow.Flow

class ProjectRepository(
    private val projectDao: ProjectDao,
    private val syncService: SyncService
) {
    fun getAllProjects(): Flow<List<Project>> = projectDao.getAllProjects()

    fun getProject(id: Long): Flow<Project?> = projectDao.getProject(id)

    suspend fun insertProject(project: Project): Long {
        val id = projectDao.insert(project)
        syncService.syncProject(project.copy(id = id))
        return id
    }

    suspend fun updateProject(project: Project) {
        projectDao.update(project)
        syncService.syncProject(project)
    }

}