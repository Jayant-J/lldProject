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

class CancelCommandTest {
    LMS lms;
    CourseSchedulingCommandHandler courseSchedulingCommandHandler;
    CancelCommand cancelCommand;
    ByteArrayOutputStream os = new ByteArrayOutputStream(100);
    PrintStream capture = new PrintStream(os);

    @BeforeEach
    void setUp() {
        lms = new LMS();
        courseSchedulingCommandHandler = new CourseSchedulingCommandHandler(lms);
        cancelCommand = new CancelCommand(courseSchedulingCommandHandler);
    }

    @Test
    public void testWrongInput_shouldThrowInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> cancelCommand.execute(new String[]{}));
    }

    @Test
    public void testWrongRegistration_shouldThrowRegistrationNotFoundException() throws InvalidParameterException {
        System.setOut(capture);
        cancelCommand.execute(new String[]{"registrationId"});
        capture.flush();
        String res = os.toString().trim();
        assertEquals("No registrations with Id : registrationId found", res);
    }
}