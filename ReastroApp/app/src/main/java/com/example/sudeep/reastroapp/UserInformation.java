package com.example.sudeep.reastroapp;

public class UserInformation {
    private String Name;
    private String Email;
    private String Phone_Number;
    private String Time;
    private String Date;
    private String Number_Of_Person;

    public UserInformation(String name, String email, String phone_Number, String time, String date, String number_Of_Person) {
        Name = name;
        Email = email;
        Phone_Number = phone_Number;
        Time = time;
        Date = date;
        Number_Of_Person = number_Of_Person;
    }

    public UserInformation() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNumber_Of_Person() {
        return Number_Of_Person;
    }

    public void setNumber_Of_Person(String number_Of_Person) {
        Number_Of_Person = number_Of_Person;
    }
}
