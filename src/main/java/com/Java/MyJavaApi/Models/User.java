package com.Java.MyJavaApi.Models;

import java.time.LocalDateTime;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private boolean active;
    private int balance;
    private LocalDateTime createdDate;

    // Constructor
    public User() {
        this.createdDate = LocalDateTime.now();
    }

    public User(long id, String firstName, String lastName, boolean active, int balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.balance = balance;
        this.createdDate = LocalDateTime.now();
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}
