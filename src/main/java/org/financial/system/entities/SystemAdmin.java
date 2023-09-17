package org.financial.system.entities;

import java.util.ArrayList;
import java.util.List;

public class SystemAdmin extends Employee{

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
