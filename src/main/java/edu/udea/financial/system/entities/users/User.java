package edu.udea.financial.system.entities.users;

import jakarta.persistence.*;

@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTH0_ID", unique = true)
    protected String auth0Id;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "EMAIL", unique = true)
    protected String email;

    @Column(name = "PICTURE")
    protected String picture;

    public User() {
    }

    public User(String auth0Id, String name, String email, String picture) {
        this.auth0Id = auth0Id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
