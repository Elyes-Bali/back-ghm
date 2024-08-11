package tn.esprit.vitanova.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.vitanova.entities.ERole;
import tn.esprit.vitanova.entities.Project;
import tn.esprit.vitanova.entities.User;
import tn.esprit.vitanova.repository.ProjectRepo;
import tn.esprit.vitanova.repository.TaskRepo;
import tn.esprit.vitanova.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private ProjectRepo projectRepo;
    private UserRepo userRepo;
    private TaskRepo taskRepository;
    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Project getProject(Long id) {
        return projectRepo.findById(id).orElseThrow(() -> new RuntimeException("Project with id " + id + " not found"));
    }

    @Override
    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project existingProject = getProject(id);
        existingProject.setName(project.getName());
        return projectRepo.save(existingProject);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    @Override
    public void assignManagerToProject(Long projectId, Long managerId) {
        Project project = getProject(projectId);
        Optional<User> managerOpt = userRepo.findById(managerId);

        if (managerOpt.isPresent()) {
            User manager = managerOpt.get();
            if (manager.getRoles().stream().anyMatch(role -> role.getName() == ERole.ROLE_MANAGER)) {
                project.setManager(manager);
                projectRepo.save(project);
            } else {
                throw new RuntimeException("User with id " + managerId + " does not have ROLE_MANAGER");
            }
        } else {
            throw new RuntimeException("User with id " + managerId + " not found");
        }
    }
}
