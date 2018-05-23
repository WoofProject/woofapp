package com.enterprises.woof.woof;

public class Client {
    String name, email, password;

    public Client() {}

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword (String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
