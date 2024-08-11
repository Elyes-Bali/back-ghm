package tn.esprit.vitanova.services;

import tn.esprit.vitanova.entities.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project getProject(Long id);
    Project createProject(Project project);
    Project updateProject(Long id, Project project);
    void deleteProject(Long id);
    void assignManagerToProject(Long projectId, Long managerId);
}
