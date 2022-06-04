package com.patni.lld.model;

import java.util.Objects;

public class Employee implements Comparable<Employee> {

    int employeeId;
    String employeeName;
    String emailId;

    public Employee(int employeeId, String employeeName, String emailId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        return emailId.equals(that.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId);
    }

    @Override
    public int compareTo(Employee that) {
        return this.emailId.compareTo(that.emailId);
    }

}
