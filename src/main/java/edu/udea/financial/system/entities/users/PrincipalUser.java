package edu.udea.financial.system.entities.users;

import edu.udea.financial.system.entities.Company;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRINCIPAL_USER")
public class PrincipalUser extends User {

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Company> companies;

    public PrincipalUser() {
    }

    public PrincipalUser(String auth0Id, String name, String email, String picture) {
        super(auth0Id, name, email, picture);
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
