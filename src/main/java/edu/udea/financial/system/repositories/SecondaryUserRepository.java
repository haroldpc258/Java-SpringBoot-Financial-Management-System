package edu.udea.financial.system.repositories;

import edu.udea.financial.system.entities.users.SecondaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryUserRepository extends JpaRepository<SecondaryUser, Long> {
    SecondaryUser findByEmail(String email);
    boolean existsByEmail(String email);
}
