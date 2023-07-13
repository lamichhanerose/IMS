package com.example.imsmanagemnt.role;

public enum RoleType {
    ADMIN("Admin"),
    USER("User");

    public final String value;

    RoleType(String value) {
        this.value = value;
    }
}
