package edu.udea.financial.system.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.udea.financial.system.entities.users.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NIT")
    private String nit;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<User> employees;

    @OneToMany
    private List<FinancialTransaction> transactions;

    public Company() {
        employees = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public Company(String nit, String name, String phoneNumber, String address, List<User> employees, List<FinancialTransaction> transactions) {
        this.nit = nit;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.employees = employees;
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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
                "nit='" + nit + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'';
    }
}
