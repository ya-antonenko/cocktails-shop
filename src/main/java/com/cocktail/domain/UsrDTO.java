package com.cocktail.domain;

import javax.persistence.Column;

public class UsrDTO {
    private String username;
    private String password;
    private boolean active;
    private Roles roles;
    private String email;
    private String activationCode;

    public UsrDTO() {
    }

    public UsrDTO(String username, String password, boolean active, String activationCode,Roles roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.activationCode = activationCode;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
