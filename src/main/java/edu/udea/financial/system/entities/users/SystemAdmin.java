package edu.udea.financial.system.entities.users;

import edu.udea.financial.system.entities.Company;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SYSTEM_ADMIN")
public class SystemAdmin extends Employee {

    public SystemAdmin() {

    }

    public SystemAdmin(String name, String email, String dni, String password) {
        super(name, email, dni, password);
    }
}
