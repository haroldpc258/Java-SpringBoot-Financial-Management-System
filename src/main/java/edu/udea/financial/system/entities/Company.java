package edu.udea.financial.system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.udea.financial.system.entities.users.PrincipalUser;
import edu.udea.financial.system.entities.users.SecondaryUser;
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

    @Column(name = "NIT", unique = true)
    private String nit;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "PRINCIPAL_USER_ID")
    private PrincipalUser owner;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SecondaryUser> employees;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FinancialTransaction> transactions;

    public Company() {
        employees = new ArrayList<>();
        transactions = new ArrayList<>();
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

    public List<SecondaryUser> getEmployees() {
        return employees;
    }

    public void setEmployees(List<SecondaryUser> employees) {
        this.employees = employees;
    }

    public List<FinancialTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<FinancialTransaction> transactions) {
        this.transactions = transactions;
    }

    public PrincipalUser getOwner() {
        return owner;
    }

    public void setOwner(PrincipalUser owner) {
        this.owner = owner;
    }
}
