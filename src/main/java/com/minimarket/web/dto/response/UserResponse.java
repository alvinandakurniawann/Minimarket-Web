package com.minimarket.web.dto.response;

public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private String address;

    public UserResponse(Long id, String fullName, String email, String address) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
