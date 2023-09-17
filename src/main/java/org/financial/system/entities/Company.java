package org.financial.system.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

    private String NIT;
    private String name;
    private String phoneNumber;
    private String address;
    private Map<String, User> employees;
    private List<FinancialTransaction> transactions;

    public Company() {
        employees = new HashMap<>();
        transactions = new ArrayList<>();
    }

    public Company(String NIT, String name, String phoneNumber, String address, Map<String, User> employees, List<FinancialTransaction> transactions) {
        this.NIT = NIT;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.employees = employees;
        this.transactions = transactions;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, User> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<String, User> employees) {
        this.employees = employees;
    }

    public List<FinancialTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<FinancialTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Company -->" +
                "NIT='" + NIT + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'';
    }
}
