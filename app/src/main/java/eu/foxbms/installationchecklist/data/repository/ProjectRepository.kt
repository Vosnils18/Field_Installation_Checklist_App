package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.ProjectDao
import eu.foxbms.installationchecklist.data.local.ProjectEntity
import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val projectDao: ProjectDao) {
    fun getAllProjects(): Flow<List<ProjectEntity>> = projectDao.getAllProjects()

    fun getProjectById(id: Int): Flow<ProjectEntity?> = projectDao.getProjectById(id)

    suspend fun insertProject(project: ProjectEntity) = projectDao.insertProject(project)

    suspend fun updateProject(project: ProjectEntity) = projectDao.updateProject(project)

    suspend fun deleteProject(id: Int) = projectDao.deleteProject(id)
}
