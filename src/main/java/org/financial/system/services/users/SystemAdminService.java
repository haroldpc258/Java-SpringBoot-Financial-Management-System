package org.financial.system.services.users;

import org.financial.system.entities.*;
import org.financial.system.entities.users.Employee;
import org.financial.system.entities.users.SystemAdmin;
import org.financial.system.entities.users.User;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SystemAdminService extends UserService {

    public SystemAdminService() {
        super();
    }

    public void createCompany(SystemAdmin systemAdmin) {

        System.out.println("\nCREANDO UNA NUEVA EMPRESA EN EL SISTEMA");
        Company newCompany = new Company();

        System.out.print("Ingrese el NIT: ");
        newCompany.setNIT(scanner.next());

        System.out.print("Ingrese el Nombre: ");
        newCompany.setName(scanner.next());

        System.out.print("Ingrese el Numéro Telefónico: ");
        newCompany.setPhoneNumber(scanner.next());

        System.out.print("Ingrese la Dirección de Ubicación: ");
        newCompany.setAddress(scanner.next());

        System.out.println("\nSe ha registrados exitosamente la Empresa " + newCompany.getName()
                + " en el sistema...");

        systemAdmin.getCompanies().add(newCompany);
        insertRegister(newCompany);
    }

    public Employee createEmployee(Company company) {

        System.out.println("\nCREANDO UN EMPLEADO");

        System.out.print("Ingrese el Nombre: ");
        String name = scanner.next();

        System.out.print("Ingrese el Email: ");
        String email = scanner.next();

        System.out.print("Ingrese el DNI: ");
        String dni = scanner.next();
        System.out.println();

        for (int i = 0; i < User.Role.values().length; i++) {
            System.out.println((i + 1) + ". " + User.Role.values()[i].getType());
        }

        System.out.print("Seleccione el tipo de usuario que será: ");
        User.Role role = User.Role.values()[scanner.nextInt() - 1];

        System.out.print("Ingrese una contraseña: ");
        String password = scanner.next();


        System.out.println("\nSe ha registrados el usuario en la Empresa " + company.getName());

        User newUser = new User(name, email, dni, password, role, company);
        addEmployeeToCompany(newUser, company);

        insertRegister(newUser);

        return newUser;
    }

    private void addEmployeeToCompany(User user, Company company) {
        company.getEmployees().add(user);
    }

    public Company selectCompany(SystemAdmin systemAdmin) {

        List<Company> companies = viewCompanies(systemAdmin);

        System.out.print("Seleccione una Empresa: ");
        return companies.get(scanner.nextInt() - 1);
    }

    public List<Company> viewCompanies(SystemAdmin systemAdmin) {

        System.out.println("\nLISTA DE EMPRESAS");

        List<Company> companies = selectEntity(Company.class);

        for (int i = 0; i < companies.size(); i++) {
            System.out.println((i + 1) + ". " + companies.get(i));
        }

        return companies;
    }

    public void viewEmployees(Company company) {

        System.out.println("\nLISTA DE EMPLEADOS");

        List<User> users = selectEntity(User.class);

        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }


}
