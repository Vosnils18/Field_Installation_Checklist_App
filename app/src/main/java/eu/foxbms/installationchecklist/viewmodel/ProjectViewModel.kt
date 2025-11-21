package eu.foxbms.installationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.foxbms.installationchecklist.data.local.Project
import kotlinx.coroutines.launch
import eu.foxbms.installationchecklist.data.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProjectViewModel(private val projectRepository: ProjectRepository) : ViewModel() {
    fun insertProject(project: Project) {
        viewModelScope.launch {
            projectRepository.insertProject(project)
        }
    }

    fun updateProject(project: Project) {
        viewModelScope.launch {
            projectRepository.updateProject(project)
        }
    }

    fun getAllProjects(): Flow<List<Project>> {
        return projectRepository.getAllProjects()
    }

    fun getProject(projectId: Long): Flow<Project?> {
        return projectRepository.getProject(projectId)
    }
}
