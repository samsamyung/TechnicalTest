package com.samyung.param;

import com.samyung.entity.CustomerStatus;

public class GetCustomersCriteria {
  long id;
  String name;
  CustomerStatus status;
  long creationDateTimeFrom;
  long creationDateTimeTo;
  String phone;
  String address;
  String email;
  CustomerSortBy sortBy;
  boolean decsending;

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

  public long getCreationDateTimeFrom() {
    return creationDateTimeFrom;
  }

  public void setCreationDateTimeFrom(long creationDateTimeFrom) {
    this.creationDateTimeFrom = creationDateTimeFrom;
  }

  public long getCreationDateTimeTo() {
    return creationDateTimeTo;
  }

  public void setCreationDateTimeTo(long creationDateTimeTo) {
    this.creationDateTimeTo = creationDateTimeTo;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CustomerSortBy getSortBy() {
    return sortBy;
  }

  public void setSortBy(CustomerSortBy sortBy) {
    this.sortBy = sortBy;
  }

  public boolean isDecsending() {
    return decsending;
  }

  public void setDecsending(boolean decsending) {
    this.decsending = decsending;
  }

  @Override
  public String toString() {
    return "GetCustomersCriteria{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", status=" + status +
            ", creationDateTimeFrom=" + creationDateTimeFrom +
            ", creationDateTimeTo=" + creationDateTimeTo +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", sortBy=" + sortBy +
            ", decsending=" + decsending +
            '}';
  }
}
