package edu.udea.financial.system.services;

import edu.udea.financial.system.entities.users.SecondaryUser;
import edu.udea.financial.system.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    SecondaryUserService secondaryUserService;
    @Autowired
    PrincipalUserService principalUserService;

    public String verifyRoleByEmail(OidcUser profile) {

        String email = profile.getEmail();

        if (secondaryUserService.userExistsByEmail(email)) {
            SecondaryUser user = secondaryUserService.findByEmail(email);
            if (user.getRole().equals(SecondaryUser.Role.ADMINISTRATOR)) {
                return "MODERATOR";
            }
            return "OPERATOR";
        }
        return "ADMIN";
    }

    public User getUserByEmail(String email) {
        if (secondaryUserService.userExistsByEmail(email)) {
            return secondaryUserService.findByEmail(email);
        }
        return principalUserService.findByEmail(email);
    }
}
