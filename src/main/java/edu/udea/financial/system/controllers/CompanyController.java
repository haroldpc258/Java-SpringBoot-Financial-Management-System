package edu.udea.financial.system.controllers;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/enterprises")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping()
    public String getCompanies(Model model) {
        model.addAttribute("companies",  companyService.getCompanies());
        return "companiesView";
    }

    @GetMapping("/create")
    public String createCompany(Model model) {
        model.addAttribute("newCompany",  new Company());
        return "createCompanyView";
    }

    @GetMapping("/{id}")
    public String manageCompany(@PathVariable Long id, Model model) {
        model.addAttribute("company",  companyService.getCompanyById(id));
        return "companyView";
    }

    @GetMapping("/{id}/update")
    public String updateCompany(@PathVariable Long id, Model model) {
        model.addAttribute("company",  companyService.getCompanyById(id));
        return "updateCompanyView";
    }

    @PostMapping("/create")
    public RedirectView createCompany(@ModelAttribute Company company) {
        companyService.createCompany(company);
        return new RedirectView("/enterprises/" + company.getId());
    }

    @PutMapping("/{id}/update")
    public RedirectView updateCompany(@ModelAttribute Company company) {
        companyService.createCompany(company);
        return new RedirectView("/enterprises");
    }
    @DeleteMapping ("/{id}")
    public RedirectView deleteCompanyById(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
        return new RedirectView("/enterprises");
    }

    /*@GetMapping
    public ResponseEntity<?> getCompanies() {
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
    }*/

    /*@GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        if (companyService.companyExists(id)) {
            return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
        }
        return companyNotFound(id);
    }*/

    /*@PatchMapping("/{id}")
    public ResponseEntity<?> patchCompanyById(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        ResponseEntity<?> response;
        if (companyService.companyExists(id)) {
            try {
                response = new ResponseEntity<>(companyService.patchCompanyById(id, updates), HttpStatus.OK);
            } catch (Exception e) {
                response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return response;
        }
        return companyNotFound(id);
    }*/

  /*  private ResponseEntity<?> companyNotFound(Long id) {
        return new ResponseEntity<>("No se encontró la compañía con ID: " + id, HttpStatus.NOT_FOUND);
    }*/

}
