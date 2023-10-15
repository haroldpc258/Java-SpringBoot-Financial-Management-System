package edu.udea.financial.system.repositories;

import edu.udea.financial.system.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
