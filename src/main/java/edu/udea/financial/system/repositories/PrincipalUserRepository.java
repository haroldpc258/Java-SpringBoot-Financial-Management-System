package edu.udea.financial.system.repositories;

import edu.udea.financial.system.entities.users.PrincipalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrincipalUserRepository extends JpaRepository<PrincipalUser, Long> {
    PrincipalUser findByEmail(String email);
}
