package tn.esprit.vitanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.vitanova.entities.Sale;

public interface SaleRepo extends JpaRepository<Sale, Long> {

}
