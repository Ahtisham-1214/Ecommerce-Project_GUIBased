package Backend.entities;
import Model.DatabaseConnection;


import java.sql.*;


public class Customer {
    private int CustomerId;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private String customerType;


    public Customer(String name, String customerType, String email, String phoneNumber, String address) {
        this.email = email;
        this.customerType = customerType;      // '1 for known customer' or '2 for Walk in customer'
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getCustomerId() {
        return CustomerId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void display() {
        System.out.println("------ User Details ------");
        System.out.println("Customer ID: " + CustomerId);
        System.out.println("Email: " + email);
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("--------------------------");
    }



}
