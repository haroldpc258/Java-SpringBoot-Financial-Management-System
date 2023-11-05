package edu.udea.financial.system.services;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.entities.FinancialTransaction;
import edu.udea.financial.system.entities.users.PrincipalUser;
import edu.udea.financial.system.entities.users.SecondaryUser;
import edu.udea.financial.system.entities.users.User;
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
    @Autowired
    private PrincipalUserService principalUserService;
    @Autowired private SecondaryUserService secondaryUserService;

    public List<FinancialTransaction> getFinancialTransactions(Long companyId) {
        System.out.println(companyService.getCompanyById(companyId).getTransactions());
        return companyService.getCompanyById(companyId).getTransactions();
    }

    public FinancialTransaction createFinancialTransaction(Long userId, Long companyId, FinancialTransaction transaction) {

        User user;
        if (secondaryUserService.userExistsById(userId)) {
            user = secondaryUserService.getUserById(userId);
            transaction.setCreatedBy(user);
            secondaryUserService.saveUser((SecondaryUser) user);
        } else {
            user = principalUserService.getUserById(userId);
            transaction.setCreatedBy(user);
            principalUserService.saveUser((PrincipalUser) user);
        }

        Company company =  companyService.getCompanyById(companyId);
        company.getTransactions().add(transaction);
        companyService.saveCompany(company);

        return saveTransaction(transaction);
    }


    public FinancialTransaction getTransactionById(Long id) {
        return financialTransactionRepository.findById(id).get();
    }

    public FinancialTransaction updateTransaction(FinancialTransaction updatedTransaction) {
        FinancialTransaction transaction = getTransactionById(updatedTransaction.getId());
        updatedTransaction.setCreatedBy(transaction.getCreatedBy());
        updatedTransaction.setCreatedOn(transaction.getCreatedOnLocalDate());
        return financialTransactionRepository.save(updatedTransaction);
    }

    public FinancialTransaction saveTransaction(FinancialTransaction transaction) {
        return financialTransactionRepository.save(transaction);
    }

    public void deleteFinancialTransactionById(Long companyId, Long id) {
        Company company =  companyService.getCompanyById(companyId);
        company.getTransactions().remove(getTransactionById(id));
        companyService.saveCompany(company);
        financialTransactionRepository.deleteById(id);
    }

}
