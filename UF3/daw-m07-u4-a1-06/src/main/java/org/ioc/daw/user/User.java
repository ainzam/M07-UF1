package org.ioc.daw.user;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String username;
    private String name;
    private String email;
    private int rank;
    private Timestamp createdOn;
    private boolean active;
    private String password;

    public User(int userId, String username, String name, String email, int rank, Timestamp createdOn, boolean active, String password) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.email = email;
        this.rank = rank;
        this.createdOn = createdOn;
        this.active = active;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getRank() {
        return rank;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public boolean isActive() {
        return active;
    }
    
    public String getPassword() {
        return password;
    }
}
