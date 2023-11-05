package edu.udea.financial.system.controllers;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.entities.users.PrincipalUser;
import edu.udea.financial.system.services.CompanyService;
import edu.udea.financial.system.services.PrincipalUserService;
import edu.udea.financial.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/{userId}/enterprises")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public String getCompanies(@PathVariable Long userId, @AuthenticationPrincipal OidcUser profile, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("companies",  companyService.getCompanies(userId));
        return "companiesView";
    }

    @GetMapping("/create")
    public String createCompany(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("newCompany",  new Company());
        return "createCompanyView";
    }

    @PostMapping("/create")
    public RedirectView createCompany(@PathVariable Long userId, @ModelAttribute Company company) {
        companyService.createCompany(userId, company);
        return new RedirectView("/" + userId + "/enterprises/" + company.getId());
    }

    @GetMapping("/{id}")
    public String manageCompany(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long id, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("company",  companyService.getCompanyById(id));
        return "companyView";
    }

    @GetMapping("/{id}/update")
    public String updateCompany(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long id, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("company",  companyService.getCompanyById(id));
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        return "updateCompanyView";
    }

    @PutMapping("/{id}/update")
    public RedirectView updateCompany(@PathVariable Long userId, @ModelAttribute Company company) {
        companyService.updateCompany(company);
        return new RedirectView("/" + userId + "/enterprises/" + company.getId());
    }
    @DeleteMapping ("/{id}")
    public RedirectView deleteCompanyById(@PathVariable Long userId, @PathVariable Long id) {
        companyService.deleteCompanyById(userId, id);
        return new RedirectView("/" + userId + "/enterprises");
    }

}
