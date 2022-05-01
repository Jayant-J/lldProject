package com.patni.lld.model;

import java.util.List;

public class Account {
    String name;
    String email;
    String phoneNumber;
    String userName;
    String password;
    List<Address> shippingAdresses;
    AccountStatus accountStatus;
}

class Address {
    int pinCode; //ZipCode
    String street;
    String city;
    String state;
    String country;
}

enum AccountStatus {
    ACTVE, BLOCKED, INACTIVE;
}

