package com.enterprises.woof.woof;

public class User {
    ID_Creator id_creator = new ID_Creator();
    private String email;
    private String username;
    private String password;
    private int id_number;
    private int numberOfDogs;

    public User (String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.id_number = id_creator.new_ID();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getId_number() {
        return id_number;
    }

    public void setId_number(int id_number) {
        this.id_number = id_number;
    }

    public int getNumberOfDogs() {
        return numberOfDogs;
    }

    public void setNumberOfDogs(int numberOfDogs) {
        this.numberOfDogs = numberOfDogs;
    }
}
