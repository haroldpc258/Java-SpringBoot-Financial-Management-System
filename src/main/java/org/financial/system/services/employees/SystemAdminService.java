package org.financial.system.services.employees;

import org.financial.system.entities.*;

public class SystemAdminService extends UserService {

    public SystemAdminService() {
        super();
    }

    public Company createCompany() {

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

        return newCompany;
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

        return newUser;
    }

    private void addEmployeeToCompany(User user, Company company) {
        company.getEmployees().put(user.getDni(), user);
    }

    public Company selectCompany(SystemAdmin systemAdmin) {

        viewCompanies(systemAdmin);

        System.out.print("Seleccione una Empresa: ");
        return systemAdmin.getCompanies().get(scanner.nextInt() - 1);
    }

    public void viewCompanies(SystemAdmin systemAdmin) {

        System.out.println("\nLISTA DE EMPRESAS");

        for (int i = 0; i < systemAdmin.getCompanies().size(); i++) {
            System.out.println((i + 1) + ". " + systemAdmin.getCompanies().get(i));
        }
    }

    public void viewEmployees(Company company) {

        System.out.println("\nLISTA DE EMPLEADOS");

        int indice = 1;
        for (User user : company.getEmployees().values()) {
            System.out.println(indice + ". " + user);
            indice++;
        }

    }

}
