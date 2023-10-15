package edu.udea.financial.system.repositories;

import edu.udea.financial.system.entities.FinancialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
}
