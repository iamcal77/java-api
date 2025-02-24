package com.Java.MyJavaApi.Models;

public class Product {
    private long userId;
    private int amount;
    private String name;

    public Product(long userId, int amount, String name) {
        this.userId = userId;
        this.amount = amount;
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}