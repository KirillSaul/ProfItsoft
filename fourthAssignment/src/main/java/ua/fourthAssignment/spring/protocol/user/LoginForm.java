package ua.fourthAssignment.spring.protocol.user;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class LoginForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginForm loginForm = (LoginForm) o;
        return username.equals(loginForm.username) && password.equals(loginForm.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
