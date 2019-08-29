package com.kkh.kronikare;

public class User {
    //private String Email;
    private String Name;
    private String Phone;

    public User(String name, String phone) {
        //this.Email = email;
        this.Name = name;
        this.Phone = phone;
    }

//    public String getEmail() {
//        return Email;
//    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

//    public void setEmail(String email) {
//        Email = email;
//    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
