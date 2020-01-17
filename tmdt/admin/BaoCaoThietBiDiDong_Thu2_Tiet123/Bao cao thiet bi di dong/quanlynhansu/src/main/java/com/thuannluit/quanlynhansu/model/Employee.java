package com.thuannluit.quanlynhansu.model;

import javax.persistence.*;

@Entity
//@Entity cong dung mapping lop employee nay voi bang Employee trong database
// để chúng ta có thể thao tác với database sử dụng entity này
@Table(name = "Employee", schema = "quanlynhansu")
//@Table thay đổi tên bảng của database
// sẽ sử dụng trong ứng dụng của mình mà không thay đổi tên của entity
public class Employee {
    private int id;
    private String no;
    private String name;
    private String birthday;
    private String gender;
    private String phone;
    private String email;
    private String address;

    public Employee() {
    }

    @Id // chi dinh cot nay la primarykey
    @Column(name = "id") // mapping thuộc tính id này với id trong database
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic // chi dinh cot binh thuong ko phai khoa chinh
    @Column(name = "no")// mapping thuộc tính no này với no trong database
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Basic
    @Column(name = "name") // mapping thuộc tính name này với name trong database
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "birthday") // mapping thuộc tính birthday này với birthday trong database
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "gender") // mapping thuộc tính gender này với gender trong database
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "phone") // mapping thuộc tính phone này với phone trong database
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email") // mapping thuộc tính email này với email trong database
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address") // mapping thuộc tính address này với address trong database
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
