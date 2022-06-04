package com.patni.lld.client;

import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.interaction.CommandFactory;
import com.patni.lld.model.LMS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ClientTests {
    LMS lms;
    CommandFactory commandFactory;

    @BeforeEach
    public void setup() {
        lms=new LMS();
        CourseSchedulingCommandHandler courseSchedulingCommandHandler = new CourseSchedulingCommandHandler(lms);
        commandFactory = CommandFactory.init(courseSchedulingCommandHandler);
    }

    @Test
    public void handleInput_shouldHandleInput() {
        Client client = new CLIClient(new BufferedReader(new StringReader("exit")), commandFactory);
        assertDoesNotThrow(() -> client.handleInput());
    }
}
