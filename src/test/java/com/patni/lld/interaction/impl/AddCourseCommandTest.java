package com.patni.lld.interaction.impl;

import com.patni.lld.exception.InvalidParameterException;
import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.model.LMS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddCourseCommandTest {
    LMS lms;
    CourseSchedulingCommandHandler courseSchedulingCommandHandler;
    AddCourseCommand addCourseCommand;
    ByteArrayOutputStream os = new ByteArrayOutputStream(100);
    PrintStream capture = new PrintStream(os);


    @BeforeEach
    void setUp() {
        lms = new LMS();
        courseSchedulingCommandHandler = new CourseSchedulingCommandHandler(lms);
        addCourseCommand = new AddCourseCommand(courseSchedulingCommandHandler);
    }

    @Test
    public void testWrongInput_shouldThrowInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> addCourseCommand.execute(new String[]{}));
    }

    @Test
    public void testWrongInputDate_shouldThrowInvalidParameterException() throws InvalidParameterException {
        System.setOut(capture);
        addCourseCommand.execute(new String[]{"courseName", "instructor", "24/Apr/2020", "2", "3"});
        capture.flush();
        String res = os.toString().trim();
        assertEquals("INPUT_DATA_ERROR", res);
    }

    @Test
    public void testWrongInputEmployeeNumber_shouldThrowInvalidParameterException() throws InvalidParameterException {
        System.setOut(capture);
        addCourseCommand.execute(new String[]{"courseName", "instructor", "24042023", "2", "a"});
        capture.flush();
        String res = os.toString().trim();
        assertEquals("INPUT_DATA_ERROR", res);
    }

    @Test
    public void testWrongInputMinMaxEmployee() throws InvalidParameterException {
        System.setOut(capture);
        addCourseCommand.execute(new String[]{"courseName", "instructor", "24042023", "2", "1"});
        capture.flush();
        String res = os.toString().trim();
        assertEquals("Course Not Created, Make sure to have minCount<=maxCount", res);
    }

    @Test
    public void testSuccess() throws InvalidParameterException {
        System.setOut(capture);
        addCourseCommand.execute(new String[]{"courseName", "instructor", "24042023", "2", "3"});
        capture.flush();
        String res = os.toString().trim();
        assertEquals("OFFERING-courseName-instructor", res);
    }
}