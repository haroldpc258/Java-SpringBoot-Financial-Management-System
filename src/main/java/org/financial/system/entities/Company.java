package org.financial.system.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.financial.system.entities.users.User;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @Column(name = "NIT")
    private String NIT;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> employees;

    @ManyToMany
    @JoinTable(name = "COMPANY_TRANSACTION",
            joinColumns = @JoinColumn(name = "COMPANY_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID"))
    private List<FinancialTransaction> transactions;

    public Company() {
        employees = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public Company(String NIT, String name, String phoneNumber, String address, List<User> employees, List<FinancialTransaction> transactions) {
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

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
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
