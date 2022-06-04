package com.patni.lld.interaction.impl;

import com.patni.lld.exception.InvalidParameterException;
import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.model.Course;
import com.patni.lld.model.Instructor;
import com.patni.lld.model.LMS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RegisterCommandTest {
    LMS lms;
    CourseSchedulingCommandHandler courseSchedulingCommandHandler;
    RegisterCommand registerCommand;
    ByteArrayOutputStream os = new ByteArrayOutputStream(100);
    PrintStream capture = new PrintStream(os);

    @BeforeEach
    void setUp() {
        lms = new LMS();
        courseSchedulingCommandHandler = new CourseSchedulingCommandHandler(lms);
        registerCommand = new RegisterCommand(courseSchedulingCommandHandler);
    }

    @Test
    public void testWrongInput_shouldThrowInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> registerCommand.execute(new String[]{}));
    }

    @Test
    public void testWrongCourseId_shouldThrowCourseNotFoundException() throws InvalidParameterException {
        System.setOut(capture);
        registerCommand.execute(new String[]{"email", "courseId"});
        capture.flush();
        String res = os.toString().trim();
        assertEquals("No course exists with Id : courseId", res);
    }

    @Test
    public void testSameEnrollment_shouldThrowEmployeeAlreadyRegisteredException() throws InvalidParameterException {
        System.setOut(capture);
        Course course = new Course("title", new Instructor("instructor"), LocalDate.now().plusMonths(2L), 1, 3);
        lms.getCourseMap().put(course.getCourseId(), course);
        registerCommand.execute(new String[]{"email", course.getCourseId()});
        registerCommand.execute(new String[]{"email", course.getCourseId()});
        capture.flush();
        String res = os.toString().trim();
        assertTrue(res.endsWith("Employee with email : email Already Registered for course : title"));
    }
}