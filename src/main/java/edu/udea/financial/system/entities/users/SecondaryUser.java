package edu.udea.financial.system.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.udea.financial.system.entities.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "SECONDARY_USER")
public class SecondaryUser extends User {

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "DNI", unique = true)
    protected String dni;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    public SecondaryUser() {
    }

    public SecondaryUser(String auth0Id, String name, String email, String picture, Role role, String dni, Company company) {
        super(auth0Id, name, email, picture);
        this.role = role;
        this.dni = dni;
        this.company = company;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public enum Role {
        ADMINISTRATOR("Administrador"),
        OPERATIONAL("Operativo");

        final String type;

        Role(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

}
