package edu.udea.financial.system.controllers;

import edu.udea.financial.system.entities.users.PrincipalUser;
import edu.udea.financial.system.entities.users.SecondaryUser;
import edu.udea.financial.system.services.PrincipalUserService;
import edu.udea.financial.system.services.SecondaryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PrincipalUserService principalUserService;
    @Autowired
    private SecondaryUserService secondaryUserService;

    @GetMapping("/")
    public String home(Model model,  @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            if (secondaryUserService.userExistsByEmail(principal.getEmail())) {
                SecondaryUser user = secondaryUserService.getUser(principal);
                model.addAttribute("user", user);
            } else {
                PrincipalUser user = principalUserService.getOrCreateUser(principal);
                model.addAttribute("user", user);
            }
        }
        return "index";
    }

}
