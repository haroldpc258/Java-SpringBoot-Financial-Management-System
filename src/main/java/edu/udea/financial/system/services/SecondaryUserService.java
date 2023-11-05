package edu.udea.financial.system.services;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.entities.users.PrincipalUser;
import edu.udea.financial.system.entities.users.SecondaryUser;
import edu.udea.financial.system.repositories.SecondaryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondaryUserService {

    @Autowired
    private SecondaryUserRepository secondaryUserRepository;
    @Autowired
    private CompanyService companyService;

    public List<SecondaryUser> getUsers() {
        return secondaryUserRepository.findAll();
    }

    public SecondaryUser findByEmail(String email) {
        return secondaryUserRepository.findByEmail(email);
    }

    public SecondaryUser createUser(Long companyId, SecondaryUser secondaryUser) {
        Company company =  companyService.getCompanyById(companyId);
        company.getEmployees().add(secondaryUser);
        companyService.saveCompany(company);
        secondaryUser.setCompany(company);
        return secondaryUserRepository.save(secondaryUser);
    }

    public SecondaryUser getUser(OidcUser principal) {

        SecondaryUser secondaryUser = findByEmail(principal.getEmail());

        if (secondaryUser.getAuth0Id() == null) {
            secondaryUser.setPicture(principal.getPicture());
            secondaryUser.setEmail(principal.getEmail());
            secondaryUser.setAuth0Id(principal.getSubject());
            saveUser(secondaryUser);
        }

        return secondaryUser;
    }

    public SecondaryUser getUserById(Long id) {
        return secondaryUserRepository.findById(id).get();
    }

    public SecondaryUser saveUser(SecondaryUser secondaryUser) {
        return secondaryUserRepository.save(secondaryUser);
    }

    public SecondaryUser updateSecondaryUser(SecondaryUser updatedUser) {
        SecondaryUser user = getUserById(updatedUser.getId());
        updatedUser.setCompany(user.getCompany());
        updatedUser.setAuth0Id(user.getAuth0Id());
        updatedUser.setPicture(user.getPicture());
        return saveUser(updatedUser);
    }

    public void deleteUserById(Long companyId, Long id) {
        Company company = companyService.getCompanyById(companyId);
        company.getEmployees().remove(getUserById(id));
        companyService.saveCompany(company);
        secondaryUserRepository.deleteById(id);
    }

    public boolean userExistsById(Long id) {
        return secondaryUserRepository.existsById(id);
    }

    public boolean userExistsByEmail(String email) {
        return secondaryUserRepository.existsByEmail(email);
    }
}
