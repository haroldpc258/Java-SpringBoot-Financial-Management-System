package org.financial.system.services.employees;

import org.financial.system.entities.*;

import java.util.Scanner;


public class UserService {

    Scanner scanner;

    public UserService() {
        scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void viewTransactions(Company company) {

        System.out.println("\nLISTA DE MOVIMIENTOS DE DINERO");

        for (int i = 0; i < company.getTransactions().size(); i++) {
            System.out.println((i + 1) + ". " + company.getTransactions().get(i));
        }
    }

    public void makeTransaction(Company company, Employee user) {

        if (checkPermission(user)) {
            System.out.println("\nREALIZANDO MOVIMIENTO DE DINERO");

            FinancialTransaction transaction = new FinancialTransaction();

            for (int i = 0; i < FinancialTransaction.Type.values().length; i++) {
                System.out.println((i + 1) + ". " + FinancialTransaction.Type.values()[i].getType());
            }
            System.out.print("Seleccione el tipo de Movimiento de Dinero: ");
            transaction.setType(FinancialTransaction.Type.values()[scanner.nextInt() - 1]);

            System.out.print("\nIngrese el monto: ");
            transaction.setAmount(scanner.nextDouble());

            System.out.println("Ingrese toda la información relaciona con el concepto de este Movimiento de Dinero: ");
            transaction.setConcept(scanner.next());

            transaction.setCreatedBy(user);
            company.getTransactions().add(transaction);
        } else {
            System.out.println("\nLo siento, no tiene permisos para realizar esta acción...");
        }

    }

    protected boolean checkPermission(Employee user) {
        if (user instanceof SystemAdmin) {
            return true;
        } else {
            return ((User) user).getRole().equals(User.Role.ADMINISTRATOR);
        }
    }

}
