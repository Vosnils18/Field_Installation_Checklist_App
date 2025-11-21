package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.ProjectDao
import eu.foxbms.installationchecklist.data.local.Project
import kotlinx.coroutines.flow.Flow

// TODO: Change these functions or delete them to conform to the new architecture
class ProjectRepository(private val projectDao: ProjectDao) {
    suspend fun insertProject(project: Project): Long {
        return projectDao.insert(project)
    }

    suspend fun updateProject(project: Project) {
        projectDao.update(project)
    }

    fun getAllProjects(): Flow<List<Project>> {
        return projectDao.getAllProjects()
    }

    fun getProject(projectId: Long): Flow<Project?> {
        return projectDao.getProject(projectId)
    }
}