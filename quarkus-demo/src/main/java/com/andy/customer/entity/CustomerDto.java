package com.andy.customer.entity;

import java.time.Instant;
import java.util.UUID;

public class CustomerDto {
    private UUID uuid;
    private String name;
    private String email;
    private CustomerType customerType;
    private Instant createdAt;

    public CustomerDto() {
    }

    public CustomerDto(UUID uuid, String name, String email, CustomerType customerType, Instant createdAt) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.customerType = customerType;
        this.createdAt = createdAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", customerType=" + customerType +
                ", createdAt=" + createdAt +
                '}';
    }
}
