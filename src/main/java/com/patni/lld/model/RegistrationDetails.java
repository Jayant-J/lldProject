package com.patni.lld.model;

public class RegistrationDetails {
    String RegistrationId;
    Course course;
    Employee employee;

    public RegistrationDetails(String registrationId, Course course, Employee employee) {
        RegistrationId = registrationId;
        this.course = course;
        this.employee = employee;
    }

    public Course getCourse() {
        return course;
    }

    public Employee getEmployee() {
        return employee;
    }

}
