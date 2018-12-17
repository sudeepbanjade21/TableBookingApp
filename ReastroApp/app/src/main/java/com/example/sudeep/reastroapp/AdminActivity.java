package com.example.sudeep.reastroapp;


public class AdminActivity {

    private String Email,Password;

    public AdminActivity() {
    }

    public AdminActivity(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
