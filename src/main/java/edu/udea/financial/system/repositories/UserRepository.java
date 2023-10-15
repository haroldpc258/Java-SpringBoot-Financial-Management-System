package edu.udea.financial.system.repositories;

import edu.udea.financial.system.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
