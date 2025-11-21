package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.ProjectDao
import eu.foxbms.installationchecklist.data.local.Project
import kotlinx.coroutines.flow.Flow

// TODO: Change these functions or delete them to conform to the new architecture
class ProjectRepository(private val projectDao: ProjectDao) {
    fun getAllProjects(): Flow<List<Project>> = projectDao.getAllProjects()

    fun getProjectById(id: Int): Flow<Project?> = projectDao.getProjectById(id)

    suspend fun insertProject(project: Project) = projectDao.insertProject(project)

    suspend fun updateProject(project: Project) = projectDao.updateProject(project)

    suspend fun deleteProject(id: Int) = projectDao.deleteProject(id)
}
