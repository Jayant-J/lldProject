package com.patni.lld.handler;

import com.patni.lld.exception.*;
import com.patni.lld.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseSchedulingCommandHandlerTest {
    LMS lms;
    CourseSchedulingCommandHandler courseSchedulingCommandHandler;
    ByteArrayOutputStream os = new ByteArrayOutputStream(100);
    PrintStream capture = new PrintStream(os);

    @BeforeEach
    void setUp() {
        lms = new LMS();
        courseSchedulingCommandHandler = new CourseSchedulingCommandHandler(lms);
    }

    @Test
    void testAddCourse_Success() throws InvalidEmployeeCountProvidedException {
        System.setOut(capture);
        courseSchedulingCommandHandler.addCourse("courseName", "instructorName", LocalDate.now().plusMonths(1L), 1, 3);
        capture.flush();
        String res = os.toString().trim();
        assertEquals("OFFERING-courseName-instructorName", res);
    }

    @Test
    void testAddCourse_Fail() {
        assertThrows(InvalidEmployeeCountProvidedException.class, () -> courseSchedulingCommandHandler.addCourse("courseName", "instructorName", LocalDate.now().plusMonths(1L), 3, 1));
    }

    @Test
    void testRegister_shouldThrowCourseNotFoundException() {
        assertThrows(CourseNotFoundException.class, () -> courseSchedulingCommandHandler.registerForCourse("employeeEmail", "unknownCourse"));
    }


    @Test
    void testRegister_shouldThrowAlreadyRegisteredException() {
        Employee employee = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().plusMonths(2L), 1, 3);
        course.getEnrolledEmployeesList().add(employee);
        lms.getCourseMap().put(course.getCourseId(), course);
        assertThrows(EmployeeAlreadyRegisteredException.class, () -> courseSchedulingCommandHandler.registerForCourse("employeeEmail", course.getCourseId()));
    }

    @Test
    void testRegister_shouldThrowBulkFullException() {
        Employee employee1 = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().plusMonths(2L), 1, 1);
        course.getEnrolledEmployeesList().add(employee1);
        lms.getCourseMap().put(course.getCourseId(), course);
        assertThrows(BatchFullException.class, () -> courseSchedulingCommandHandler.registerForCourse("employeeEmail2", course.getCourseId()));
    }

    @Test
    void testRegister_success() throws CourseNotFoundException, EmployeeAlreadyRegisteredException, BatchFullException {
        Employee employee = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().plusMonths(2L), 1, 2);
        lms.getCourseMap().put(course.getCourseId(), course);
        System.setOut(capture);
        courseSchedulingCommandHandler.registerForCourse("employeeEmail", course.getCourseId());
        capture.flush();
        String res = os.toString().trim();
        assertEquals("REG-COURSE-employeeEmail-title ACCEPTED", res);
    }

    @Test
    void testCancelRegistration_shouldThrowRegistrationNotFoundException() throws CourseNotFoundException, EmployeeAlreadyRegisteredException, BatchFullException {
        assertThrows(RegistrationNotFoundException.class, () -> courseSchedulingCommandHandler.cancelRegistration("unknownRegistrationId"));
    }

    @Test
    void testCancelRegistration_shouldBeRejected() throws CourseNotFoundException, RegistrationNotFoundException {
        Employee employee = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().plusMonths(2L), 1, 2);
        lms.getCourseMap().put(course.getCourseId(), course);
        course.setCourseStatus(CourseStatus.CONFIRMED);
        lms.getRegistrationMap().put("REG_COURSE-employeeEmail-title", new RegistrationDetails("REG_COURSE-employeeEmail-title", course, employee));
        lms.getCourseMap().get(course.getCourseId()).getEmployeeRegistrationNumberMap().put("REG_COURSE-employeeEmail-title", employee);
        System.setOut(capture);
        courseSchedulingCommandHandler.cancelRegistration("REG_COURSE-employeeEmail-title");
        capture.flush();
        String res = os.toString().trim();
        assertEquals("REG_COURSE-employeeEmail-title CANCEL_REJECTED", res);
    }

    @Test
    void testCancelRegistration_shouldBeAccepted() throws CourseNotFoundException, RegistrationNotFoundException {
        Employee employee = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().plusMonths(2L), 1, 2);
        lms.getCourseMap().put(course.getCourseId(), course);
        lms.getRegistrationMap().put("REG_COURSE-employeeEmail-title", new RegistrationDetails("REG_COURSE-employeeEmail-title", course, employee));
        lms.getCourseMap().get(course.getCourseId()).getEmployeeRegistrationNumberMap().put("REG_COURSE-employeeEmail-title", employee);
        System.setOut(capture);
        courseSchedulingCommandHandler.cancelRegistration("REG_COURSE-employeeEmail-title");
        capture.flush();
        String res = os.toString().trim();
        assertEquals("REG_COURSE-employeeEmail-title CANCEL_ACCEPTED", res);
    }

    @Test
    void testAllotCourse_shouldThrowCourseNotFoundException() {
        assertThrows(CourseNotFoundException.class, () -> courseSchedulingCommandHandler.allotCourse("unknownCourse"));
    }

    @Test
    void testAllotCourse_shouldBeCancelledWithInsufficientEmployeeException() throws CourseNotFoundException, InsufficientEmployeePresentException {
        Employee employee = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().plusMonths(2L), 1, 2);
        lms.getCourseMap().put(course.getCourseId(), course);
        lms.getCourseMap().get(course.getCourseId()).getEmployeeRegistrationNumberMap().put("REG_COURSE-employeeEmail-title", employee);
        courseSchedulingCommandHandler.allotCourse(course.getCourseId());
    }

    @Test
    void testAllotCourse_success() throws CourseNotFoundException, InsufficientEmployeePresentException {
        Employee employee = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().plusMonths(2L), 0, 2);
        lms.getCourseMap().put(course.getCourseId(), course);
        lms.getCourseMap().get(course.getCourseId()).getEmployeeRegistrationNumberMap().put("REG_COURSE-employeeEmail-title", employee);
        courseSchedulingCommandHandler.allotCourse(course.getCourseId());
    }


    @Test
    void testAllotCourse_courseDatePassed() throws CourseNotFoundException, InsufficientEmployeePresentException {
        Employee employee = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().minusMonths(2L), 0, 2);
        lms.getCourseMap().put(course.getCourseId(), course);
        lms.getCourseMap().get(course.getCourseId()).getEmployeeRegistrationNumberMap().put("REG_COURSE-employeeEmail-title", employee);
        System.setOut(capture);
        courseSchedulingCommandHandler.allotCourse(course.getCourseId());
        capture.flush();
        String res = os.toString().trim();
        assertEquals("Course offering date passed", res);
    }

    @Test
    void testAllotCourseAlreadyAlloted_shouldThowCourseNotFoundException() throws CourseNotFoundException, InsufficientEmployeePresentException {
        Employee employee = new Employee(1, "name", "employeeEmail");
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().minusMonths(2L), 0, 2);
        lms.getCourseMap().put(course.getCourseId(), course);
        course.setCourseStatus(CourseStatus.CONFIRMED);
        lms.getCourseMap().get(course.getCourseId()).getEmployeeRegistrationNumberMap().put("REG_COURSE-employeeEmail-title", employee);
        assertThrows(CourseNotFoundException.class, () -> courseSchedulingCommandHandler.allotCourse(course.getCourseId()));
    }
}