package com.samyung.entity;

import java.util.Date;

public class Customer {
  long id;
  String name;
  CustomerStatus status;
  long creationDateTime;
  String phone;
  String address1;
  String address2;
  String address3;
  String email;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CustomerStatus getStatus() {
    return status;
  }

  public void setStatus(CustomerStatus status) {
    this.status = status;
  }

  public long getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(long creationDateTime) {
    this.creationDateTime = creationDateTime;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getAddress3() {
    return address3;
  }

  public void setAddress3(String address3) {
    this.address3 = address3;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", status=" + status +
            ", creationDateTime=" + creationDateTime +
            ", phone='" + phone + '\'' +
            ", address1='" + address1 + '\'' +
            ", address2='" + address2 + '\'' +
            ", address3='" + address3 + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
