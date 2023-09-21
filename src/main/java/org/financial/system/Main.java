package org.financial.system;

import org.financial.system.entities.Company;
import org.financial.system.entities.users.SystemAdmin;
import org.financial.system.entities.users.User;
import org.financial.system.services.users.SystemAdminService;
import org.financial.system.services.users.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        SystemAdminService as = new SystemAdminService();
        UserService us = new UserService();

        List<SystemAdmin> systemAdmins = new ArrayList<>();
        SystemAdmin defaultAdmin = new SystemAdmin("admin", null, "12345", "admin");
        systemAdmins.add(defaultAdmin);

        login(scanner, systemAdmins, as, us);
    }

    public static void login(Scanner scanner, List<SystemAdmin> systemAdmins, SystemAdminService as, UserService us) {

        while (true) {
            System.out.println("\nInicia sesión");
            System.out.print("Ingrese su número de Identificación: ");
            String dni = scanner.next();
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.next();

            boolean success = false;


            for (SystemAdmin admin : systemAdmins) {
                if (dni.equals(admin.getDni()) && admin.checkPassword(password)) {
                    success = menuAdmin(scanner, admin, as);
                }

                for (Company company : admin.getCompanies()) {
                    if (company.getEmployees().containsKey(dni)) {
                        User user = company.getEmployees().get(dni);

                        if (user.checkPassword(password)) {
                            success = menuUser(scanner, user, us);
                        }
                    }
                }
            }

            if (!success) {
                System.out.println("\nVerifique sus credenciales e inténtelo de nuevo.");
            }
        }
    }

    public static boolean menuAdmin(Scanner scanner, SystemAdmin systemAdmin, SystemAdminService as) {

        int option;

        do {
            System.out.println("\nMENU");
            System.out.println("1. Crear una Empresa");
            System.out.println("2. Crear un Empleado");
            System.out.println("3. Realizar un Movimiento de Dinero");
            System.out.println("4. Mostrar lista de Empleados");
            System.out.println("5. Mostrar lista de Empresas");
            System.out.println("6. Mostrar lista de Movimientos de Dinero");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            try {
                switch (option) {
                    case 1 -> {
                        systemAdmin.getCompanies().add(as.createCompany());
                    }
                    case 2 -> {
                        if (!systemAdmin.getCompanies().isEmpty()) {
                            as.createEmployee(as.selectCompany(systemAdmin));
                        } else {
                            System.out.println("\nAún no hay empresas en la cuales registrar un empleado...");
                        }
                    }
                    case 3 -> {
                        if (!systemAdmin.getCompanies().isEmpty()) {
                            as.makeTransaction(as.selectCompany(systemAdmin), systemAdmin);
                        } else {
                            System.out.println("\nAún no hay empresas en la cuales realizar movimientos...");
                        }
                    }
                    case 4 -> {
                        if (!systemAdmin.getCompanies().isEmpty()) {
                            as.viewEmployees(as.selectCompany(systemAdmin));
                        } else {
                            System.out.println("\nAún no hay empresas en el sistema...");
                        }
                    }
                    case 5 -> {
                        if (!systemAdmin.getCompanies().isEmpty()) {
                            as.viewCompanies(systemAdmin);
                        } else {
                            System.out.println("\nAún no hay empresas en el sistema...");
                        }
                    }
                    case 6 -> {
                        if (!systemAdmin.getCompanies().isEmpty()) {
                            as.viewTransactions(as.selectCompany(systemAdmin));
                        } else {
                            System.out.println("\nAún no hay empresas en el sistema");
                        }
                    }
                    case 0 -> {
                    }
                    default -> {
                        System.out.println("\nLa opción no es válida. Inténtelo nuevamente...");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nDebe seleccionar una opción válida...");
            }

        } while (option != 0);

        return true;
    }

    public static boolean menuUser(Scanner scanner, User user, UserService us) {

        int option;

        do {
            System.out.println("\nMENU");
            System.out.println("1. Realizar un Movimiento de Dinero");
            System.out.println("2. Mostrar lista de Movimientos de Movimientos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();

            try {
                switch (option) {
                    case 1 -> {
                        us.makeTransaction(user.getCompany(), user);
                    }
                    case 2 -> {
                        us.viewTransactions(user.getCompany());
                    }
                    case 0 -> {
                    }
                    default -> {
                        System.out.println("\nLa opción no es válida. Inténtelo nuevamente...");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nDebe seleccionar una opción válida...");
            }
        } while (option != 0);

        return true;
    }
}