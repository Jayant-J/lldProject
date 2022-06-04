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

class AllotCourseCommandTest {
    LMS lms;
    CourseSchedulingCommandHandler courseSchedulingCommandHandler;
    AllotCourseCommand allotCourseCommand;
    ByteArrayOutputStream os = new ByteArrayOutputStream(100);
    PrintStream capture = new PrintStream(os);

    @BeforeEach
    void setUp() {
        lms = new LMS();
        courseSchedulingCommandHandler = new CourseSchedulingCommandHandler(lms);
        allotCourseCommand = new AllotCourseCommand(courseSchedulingCommandHandler);
    }

    @Test
    public void testWrongInput_shouldThrowInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> allotCourseCommand.execute(new String[]{}));
    }

    @Test
    public void testWrongRegistration_shouldThrowRegistrationNotFoundException() throws InvalidParameterException {
        System.setOut(capture);
        allotCourseCommand.execute(new String[]{"courseId"});
        capture.flush();
        String res = os.toString().trim();
        assertEquals("No course exists with Id : courseId", res);
    }
}