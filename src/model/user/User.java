package model.user;

import java.io.Serializable;
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

    private String email;
    private char[] password;

    public User(String email, char[] password) {
        this.email = email;
        this.password = password;
    }

}
