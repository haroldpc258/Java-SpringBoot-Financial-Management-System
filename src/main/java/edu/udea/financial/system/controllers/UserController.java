package edu.udea.financial.system.controllers;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.entities.users.SecondaryUser;
import edu.udea.financial.system.services.CompanyService;
import edu.udea.financial.system.services.SecondaryUserService;
import edu.udea.financial.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/{userId}/enterprises/{companyId}/users")
public class UserController {

    @Autowired
    private SecondaryUserService secondaryUserService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public String getUsers(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long companyId, Model model) {
        Company company = companyService.getCompanyById(companyId);
        model.addAttribute("userId",  userId);
        model.addAttribute("company", company);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("alert", false);
       return "usersView";
    }

    @GetMapping("/create")
    public String createUser(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long companyId, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("companyId",  companyId);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("newUser", new SecondaryUser());
        return "createUserView";
    }

    @PostMapping("/create")
    public RedirectView createUser(@PathVariable Long userId, @PathVariable Long companyId, @ModelAttribute SecondaryUser newUser) {
        secondaryUserService.createUser(companyId, newUser);
        return new RedirectView("/" + userId + "/enterprises/" + companyId + "/users");
    }

    @GetMapping("/{id}/update")
    public String updateUser(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long companyId, @PathVariable Long id, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("companyId", companyId);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("secondaryUser", secondaryUserService.getUserById(id));
        return "updateUserView";
    }

    @PutMapping("/{id}/update")
    public RedirectView updateUser(@PathVariable Long userId, @PathVariable Long companyId, @ModelAttribute SecondaryUser secondaryUser) {
        secondaryUserService.updateSecondaryUser(secondaryUser);
        return new RedirectView("/" + userId + "/enterprises/" + companyId + "/users");
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long companyId, @PathVariable Long id, Model model) {
        try {
            secondaryUserService.deleteUserById(companyId, id);
            return "redirect:/" + userId + "/enterprises/" + companyId + "/users";
        } catch (DataIntegrityViolationException e) {
            Company company = companyService.getCompanyById(companyId);
            model.addAttribute("userId",  userId);
            model.addAttribute("company", company);
            model.addAttribute("role", userService.verifyRoleByEmail(profile));
            model.addAttribute("alert", true);
            return "usersView";
        }
    }
}
