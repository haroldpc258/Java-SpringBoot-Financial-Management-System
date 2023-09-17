package org.financial.system.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class Employee {

    protected String name;
    protected String email;
    protected String dni;
    protected String password;
    protected boolean isLoggedIn;

    public Employee() {
    }

    public Employee(String name, String email, String dni, String password) {
        this.name = name;
        this.email = email;
        this.dni = dni;
        this.password = hashPassword(password);
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
