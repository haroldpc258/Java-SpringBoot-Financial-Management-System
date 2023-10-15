package edu.udea.financial.system.controllers;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/enterprises")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getCompanies() {
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.createCompany(company), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        if (companyService.companyExists(id)) {
            return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
        }
        return companyNotFound(id);
    }

    @PatchMapping("/{id}")
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
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long id) {
        if (companyService.companyExists(id)) {
            return new ResponseEntity<>(companyService.deleteCompanyById(id), HttpStatus.OK);
        }
        return companyNotFound(id);
    }

    private ResponseEntity<?> companyNotFound(Long id) {
        return new ResponseEntity<>("No se encontró la compañía con ID: " + id, HttpStatus.NOT_FOUND);
    }
}
