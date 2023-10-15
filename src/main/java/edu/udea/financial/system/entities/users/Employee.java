package edu.udea.financial.system.entities.users;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = User.class, name = "User"),
        @JsonSubTypes.Type(value = SystemAdmin.class, name = "SystemAdmin")
})
public abstract class Employee {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DNI")
    protected String dni;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "PASSWORD")
    protected String password;

    @Column(name = "IS_LOGGED_IN")
    protected boolean isLoggedIn;

    public Employee() {
    }

    public Employee(String dni, String name, String email, String password) {
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.password = hashPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No se pudo almacenar la contraseña debido " +
                    "a que el algoritmo de cifrado no se encuentra disponible en el servidor.");
            return null;
        }
    }

    public boolean checkPassword(String password) {
        String hashOfInput = hashPassword(password);
        return this.password.equals(hashOfInput);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " -->" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'';
    }
}
