package edu.udea.financial.system.services;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.entities.users.PrincipalUser;
import edu.udea.financial.system.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PrincipalUserService userService;

    public List<Company> getCompanies(Long userId) {
        PrincipalUser principalUser = userService.getUserById(userId);
        return principalUser.getCompanies();
    }

    public Company createCompany(Long userId, Company company) {
        PrincipalUser user = userService.getUserById(userId);
        user.getCompanies().add(company);
        userService.saveUser(user);
        company.setOwner(user);
        return companyRepository.save(company);
    }

    public Company updateCompany(Company updatedCompany) {
        Company company = getCompanyById(updatedCompany.getId());
        updatedCompany.setOwner(company.getOwner());
        updatedCompany.setEmployees(company.getEmployees());
        updatedCompany.setTransactions(company.getTransactions());
        return saveCompany(updatedCompany);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }


    public void deleteCompanyById(Long userId, Long id) {
        PrincipalUser user = userService.getUserById(userId);
        user.getCompanies().remove(getCompanyById(id));
        userService.saveUser(user);
        companyRepository.deleteById(id);
    }

    public boolean companyExists(Long id) {
        return companyRepository.existsById(id);
    }
}

