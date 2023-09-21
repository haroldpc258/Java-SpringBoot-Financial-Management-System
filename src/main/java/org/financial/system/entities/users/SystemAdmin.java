package org.financial.system.entities.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.financial.system.entities.Company;

@Entity
@Table(name = "SYSTEM_ADMIN")
public class SystemAdmin extends Employee {

    @ManyToMany
    @JoinTable(name = "SYSTEM_ADMIN_COMPANY",
            joinColumns = @JoinColumn(name = "SYSTEM_ADMIN_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMPANY_ID"))
    private List<Company> companies;

    public SystemAdmin() {
        companies = new ArrayList<>();
    }



    public SystemAdmin(String name, String email, String dni, String password)  {
        super(name, email, dni, password);
        companies = new ArrayList<>();
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return super.toString() +
                "companies=" + companies;
    }
}
