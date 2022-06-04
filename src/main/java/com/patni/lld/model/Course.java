package com.patni.lld.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
    String courseId;
    String courseTitle;
    Instructor courseInstructor;
    LocalDate courseDate;
    int minEmployees;
    int maxEmployees;
    CourseStatus courseStatus;
    List<Employee> enrolledEmployeesList;
    Map<String, Employee> employeeRegistrationNumberMap;

    public Course(String courseTitle, Instructor courseInstructor, LocalDate courseDate, int minEmployees, int maxEmployees) {
        this.courseId = "OFFERING-" + courseTitle + "-" + courseInstructor.getInstructorName();
        this.courseTitle = courseTitle;
        this.courseInstructor = courseInstructor;
        this.courseDate = courseDate;
        this.minEmployees = minEmployees;
        this.maxEmployees = maxEmployees;
        this.enrolledEmployeesList = new ArrayList<>();
        this.employeeRegistrationNumberMap = new HashMap<>();
        this.courseStatus = CourseStatus.ACTIVE;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Instructor getCourseInstructor() {
        return courseInstructor;
    }

    public LocalDate getCourseDate() {
        return courseDate;
    }

    public int getMinEmployees() {
        return minEmployees;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public List<Employee> getEnrolledEmployeesList() {
        return enrolledEmployeesList;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Map<String, Employee> getEmployeeRegistrationNumberMap() {
        return employeeRegistrationNumberMap;
    }
}
