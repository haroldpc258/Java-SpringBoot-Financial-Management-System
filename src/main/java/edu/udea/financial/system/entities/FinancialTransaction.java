package edu.udea.financial.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.udea.financial.system.entities.users.PrincipalUser;
import edu.udea.financial.system.entities.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @JoinColumn(name = "USER_ID")
    private User createdBy;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    @Column(name = "CONCEPT")
    private String concept;

    public FinancialTransaction() {
        createdOn = LocalDateTime.now();
    }

    public FinancialTransaction(Type type, Double amount, String concept) {
        this.type = type;
        this.amount = amount;
        this.concept = concept;
        createdOn = LocalDateTime.now();
    }

    public FinancialTransaction(Type type, Double amount, PrincipalUser createdBy, LocalDateTime createdOn, String concept) {
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return createdOn.format(formatter);
    }

    public LocalDateTime getCreatedOnLocalDate() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = LocalDateTime.parse(createdOn);
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
}
