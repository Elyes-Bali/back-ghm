package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.vitanova.entities.Task;


public interface TaskRepo extends JpaRepository<Task,Long> {
}
