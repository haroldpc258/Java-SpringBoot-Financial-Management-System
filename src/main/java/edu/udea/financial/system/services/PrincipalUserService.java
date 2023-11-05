package edu.udea.financial.system.services;

import edu.udea.financial.system.entities.users.PrincipalUser;
import edu.udea.financial.system.repositories.PrincipalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PrincipalUserService {

    @Autowired
    private PrincipalUserRepository principalUserRepository;

    public PrincipalUser saveUser(PrincipalUser principalUser) {
        return principalUserRepository.save(principalUser);
    }

    public List<PrincipalUser> getUsers() {
        return principalUserRepository.findAll();
    }

    public PrincipalUser findByEmail(String email) {
        return principalUserRepository.findByEmail(email);
    }

    public PrincipalUser getUserById(Long id) {
        return principalUserRepository.findById(id).get();
    }

    public PrincipalUser getOrCreateUser(OidcUser principal) {
        String email = principal.getEmail();
        String name = principal.getFullName();
        String picture = principal.getPicture();
        String auth0Id = principal.getSubject();

        PrincipalUser principalUser = findByEmail(email);

        if (principalUser == null) {
            return saveUser(new PrincipalUser(auth0Id, name, email, picture));
        }

        return principalUser;
    }
}
