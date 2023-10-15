package edu.udea.financial.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.udea.financial.system.entities.users.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "FINANCIAL_TRANSACTION")
public class FinancialTransaction {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "AMOUNT")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee createdBy;

    @Column(name = "CREATED_ON")
    private LocalDate createdOn;

    @Column(name = "CONCEPT")
    private String concept;

    public FinancialTransaction() {
        createdOn = LocalDate.now();
    }

    public FinancialTransaction(Type type, Double amount, String concept) {
        this.type = type;
        this.amount = amount;
        this.concept = concept;
        createdOn = LocalDate.now();
    }

    public FinancialTransaction(Type type, Double amount, Employee createdBy, LocalDate createdOn, String concept) {
        this.type = type;
        this.amount = amount;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.concept = concept;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = LocalDate.parse(createdOn);
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public enum Type {
        INCOME("Ingreso"),
        EXPENSE("Gasto");

        final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    @Override
    public String toString() {
        return "FinancialTransaction -->" +
                "type=" + type +
                ", amount=" + amount +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                ", concept='" + concept + '\'';
    }
}