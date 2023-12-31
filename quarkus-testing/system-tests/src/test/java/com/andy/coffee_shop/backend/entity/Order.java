package com.andy.coffee_shop.backend.entity;

public class Order {

    private String type;
    private String origin;
    private String status;

    public Order(String type, String origin) {
        this.type = type;
        this.origin = origin;
    }

    public Order() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
