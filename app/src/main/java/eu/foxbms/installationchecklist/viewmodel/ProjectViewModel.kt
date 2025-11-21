package eu.foxbms.installationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import eu.foxbms.installationchecklist.data.local.Project
import eu.foxbms.installationchecklist.data.repository.ProjectRepository
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : ViewModel() {
    val projects = projectRepository.getAllProjects()

    fun insertProject(project: Project) = viewModelScope.launch {
        projectRepository.insertProject(project)
    }

    fun updateProject(project: Project) = viewModelScope.launch {
        projectRepository.updateProject(project)
    }

    fun deleteProject(id: Int) = viewModelScope.launch {
        projectRepository.deleteProject(id)
    }
}
