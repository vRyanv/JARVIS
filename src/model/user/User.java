package model.user;

import library.fileProcess.FileProcess;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

public class User implements Serializable
{
    public String getEmail() {
        return email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String email;
    private char[] password;

    private String role;

    public User(String email, char[] password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
