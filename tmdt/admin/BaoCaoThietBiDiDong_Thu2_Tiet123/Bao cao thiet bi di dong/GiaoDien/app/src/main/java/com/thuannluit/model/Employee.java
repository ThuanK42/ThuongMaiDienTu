package com.thuannluit.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String no;
    private String name;
    private String birthday;
    private String gender;
    private String phone;
    private String email;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return "\n"+"ID: " + id + "\n" + "Ma nhan vien: " + no + "\n" + "Ho va ten: " + name + "\n"
                + "Ngay sinh: " + birthday + "\n" + "Gioi tinh: " + gender + "\n"
                + "So dien thoai: " + phone + "\n" + "Email: " + email + "\n"
                + "Dia chi: " + address+"\n";
    }
}
