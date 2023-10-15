package edu.udea.financial.system.services;

import edu.udea.financial.system.entities.Company;
import edu.udea.financial.system.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    public Company patchCompanyById(Long id, Map<String, String> updates) {

        Company company = getCompanyById(id);

        updates.forEach((key, value) -> {
            switch (key) {
                case "nit" -> company.setNit(value);
                case "name" -> company.setName(value);
                case "phoneNumber" -> company.setPhoneNumber(value);
                case "address" -> company.setAddress(value);
                default -> throw new IllegalArgumentException("Campo de actualización no válido: " + key);
            }
        });
        companyRepository.save(company);
        return company;
    }

    public String deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
        return "La compañía con ID: " + id + " se ha eliminado";
    }

    public boolean companyExists(Long id) {
        return companyRepository.existsById(id);
    }
}

