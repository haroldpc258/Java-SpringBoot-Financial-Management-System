package org.financial.system.entities.users;

import javax.persistence.*;

import org.financial.system.entities.Company;

@Entity
@Table(name = "USER")
public class User extends Employee {


    @JoinColumn(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    public User() {
    }

    public User(String name, String email, String dni, String password, Role role, Company company) {
        super(name, email, dni, password);
        this.role = role;
        this.company = company;
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
