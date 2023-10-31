package edu.udea.financial.system.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.udea.financial.system.entities.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "USER")
public class User extends Employee {

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    public User() {
    }

    public User(String name, String email, String dni, String password, Role role) {
        super(name, email, dni, password);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    @Override
    public String toString() {
        return super.toString() +
                "role=" + role;
    }
}
