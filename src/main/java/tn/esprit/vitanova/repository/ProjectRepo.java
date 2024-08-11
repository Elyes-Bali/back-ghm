package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Project;

import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project,Long> {

}
