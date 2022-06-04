package com.patni.lld.handler;

import com.patni.lld.exception.*;
import com.patni.lld.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CourseSchedulingCommandHandler {
    private LMS lms;

    public CourseSchedulingCommandHandler(LMS lms) {
        this.lms = lms;
    }

    public void addCourse(String courseName, String instructor, LocalDate courseDate, int minEmployee, int maxEmployee) throws InvalidEmployeeCountProvidedException {
        if (minEmployee >= 0 && maxEmployee >= 1 && minEmployee <= maxEmployee) {
            Course course = new Course(courseName, new Instructor(instructor), courseDate, minEmployee, maxEmployee);
            Map<String, Course> courses = lms.getCourseMap();
            courses.put(course.getCourseId(), course);
            System.out.println(course.getCourseId());
        } else {
            throw new InvalidEmployeeCountProvidedException("Course Not Created, Make sure to have minCount<=maxCount");
        }
    }

    public void registerForCourse(String employeeEmail, String courseId) throws CourseNotFoundException, EmployeeAlreadyRegisteredException, BatchFullException {
        if (!courseExists(courseId)) {
            throw new CourseNotFoundException("No course exists with Id : " + courseId);
        }
        Course course = lms.getCourseMap().get(courseId);
        Employee employee = new Employee(-1, "someName", employeeEmail);
        if (course.getEnrolledEmployeesList().contains(employee)) {
            throw new EmployeeAlreadyRegisteredException("Employee with email : " + employeeEmail + " Already Registered for course : " + course.getCourseTitle());
        }
        if (course.getEnrolledEmployeesList().size() == course.getMaxEmployees()) {
            throw new BatchFullException("COURSE_FULL_ERROR");
        }
        course.getEnrolledEmployeesList().add(employee);
        String registrationId = "REG-COURSE-" + employeeEmail.split("@")[0] + "-" + course.getCourseTitle();
        course.getEmployeeRegistrationNumberMap().put(registrationId, employee);
        lms.getRegistrationMap().put(registrationId, new RegistrationDetails(registrationId, course, employee));
        System.out.println(registrationId + " ACCEPTED");
    }

    public void cancelRegistration(String registrationId) throws RegistrationNotFoundException, CourseNotFoundException {
        if (!registrationExists(registrationId)) {
            throw new RegistrationNotFoundException("No registrations with Id : " + registrationId + " found");
        }
        String courseId = lms.getRegistrationMap().get(registrationId).getCourse().getCourseId();
        if (!courseExists(courseId)) {
            throw new CourseNotFoundException("No course exists with Id : " + courseId);
        }
        Course course = lms.getCourseMap().get(courseId);
        Employee employeeRegistrationToBeCancelled = lms.getCourseMap().get(courseId).getEmployeeRegistrationNumberMap().get(registrationId);
        if (course.getCourseStatus() == CourseStatus.ACTIVE) {
            course.getEnrolledEmployeesList().remove(employeeRegistrationToBeCancelled);
            course.getEmployeeRegistrationNumberMap().remove(registrationId);
            lms.getRegistrationMap().remove(registrationId);
            System.out.println(registrationId + " CANCEL_ACCEPTED");
        } else {
            System.out.println(registrationId + " CANCEL_REJECTED");
        }
    }

    public void allotCourse(String courseOfferingId) throws CourseNotFoundException, InsufficientEmployeePresentException {
        if (!courseExists(courseOfferingId)) {
            throw new CourseNotFoundException("No course exists with Id : " + courseOfferingId);
        }
        Course course = lms.getCourseMap().get(courseOfferingId);
        if (course.getCourseStatus() == CourseStatus.ACTIVE) {
            if (course.getCourseDate().isAfter(LocalDate.now())) {
                if (course.getEnrolledEmployeesList().size() < course.getMinEmployees()) {
                    course.setCourseStatus(CourseStatus.COURSE_CANCELED);
                    getAllocationDetails(courseOfferingId);
                } else {
                    course.setCourseStatus(CourseStatus.CONFIRMED);
                    getAllocationDetails(courseOfferingId);
                }
            } else {
                System.out.println("Course offering date passed");
            }
        } else {
            throw new CourseNotFoundException("Course cannot be allotted as it is either cancelled or already allotted");
        }
    }

    private boolean courseExists(String courseId) {
        if (lms.getCourseMap().get(courseId) != null) {
            return true;
        }
        return false;
    }

    private boolean registrationExists(String registrationId) {
        if (lms.getRegistrationMap().get(registrationId) != null) {
            return true;
        }
        return false;
    }

    private void getAllocationDetails(String courseId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYYY");
        Map<String, RegistrationDetails> registrationMap = lms.getRegistrationMap();
        for (Map.Entry<String, RegistrationDetails> each : registrationMap.entrySet()) {
            if (each.getValue().getCourse().getCourseId().equals(courseId)) {
                System.out.println(each.getKey() + " "
                        + each.getValue().getEmployee().getEmailId() + " "
                        + each.getValue().getCourse().getCourseId() + " "
                        + each.getValue().getCourse().getCourseTitle() + " "
                        + each.getValue().getCourse().getCourseInstructor().getInstructorName() + " "
                        + formatter.format(each.getValue().getCourse().getCourseDate()) + " "
                        + each.getValue().getCourse().getCourseStatus());
            }

        }
    }
}
