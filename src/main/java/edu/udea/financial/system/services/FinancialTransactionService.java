package edu.udea.financial.system.services;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.entities.FinancialTransaction;
import edu.udea.financial.system.entities.users.Employee;
import edu.udea.financial.system.repositories.FinancialTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FinancialTransactionService {

    @Autowired
    private FinancialTransactionRepository financialTransactionRepository;

    @Autowired
    private CompanyService companyService;

    public List<FinancialTransaction> getFinancialTransactions(Long companyId) {
        System.out.println(companyService.getCompanyById(companyId).getTransactions());
        return companyService.getCompanyById(companyId).getTransactions();
    }

    public FinancialTransaction createFinancialTransaction(Long companyId, FinancialTransaction transaction) {
        Company company =  companyService.getCompanyById(companyId);
        company.getTransactions().add(transaction);
        transaction = financialTransactionRepository.save(transaction);
        companyService.createCompany(company);
        return transaction;
    }

    public FinancialTransaction getTransactionById(Long id) {
        return financialTransactionRepository.findById(id).get();
    }

    public FinancialTransaction patchFinancialTransactionById(Long companyId, Long id, Map<String, Object> updates) {

        FinancialTransaction transaction = getTransactionById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "amount" -> transaction.setAmount((Double) value);
                case "concept" -> transaction.setConcept((String) value);
                case "createdOn" -> transaction.setCreatedOn((String) value);
                case "type" -> transaction.setType(FinancialTransaction.Type.valueOf((String) value));
                case "createdBy" -> transaction.setCreatedBy((Employee) value);
                default -> throw new IllegalArgumentException("Campo de actualización no válido: " + key);
            }
        });
        financialTransactionRepository.save(transaction);
        return transaction;
    }

    public String deleteFinancialTransactionById(Long companyId, Long id) {
        Company company =  companyService.getCompanyById(companyId);
        company.getTransactions().remove(getTransactionById(id));
        financialTransactionRepository.deleteById(id);
        return "La transacción: " + id + " se ha eliminado";
    }

    public boolean transactionExists(Long companyId, Long id) {
        return financialTransactionRepository.existsById(id) &&
                companyService.getCompanyById(companyId).getTransactions().contains(financialTransactionRepository.getReferenceById(id));
    }
}
