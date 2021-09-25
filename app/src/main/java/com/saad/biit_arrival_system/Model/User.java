package com.saad.biit_arrival_system.Model;

public class User {
    public int Id;
    public String FirstName;
    public String LastName;
    public String Gender;
    public String Mobile;
    public String Password;
    public String Role;

    public User()
    {

    }

    public User(int id, String firstName, String lastName, String gender, String mobile, String password, String role) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Mobile = mobile;
        Password = password;
        Role = role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
