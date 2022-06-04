package com.patni.lld.interaction;

import com.patni.lld.exception.CommandNotFoundException;
import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.model.LMS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.patni.lld.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandFactoryTest {

    LMS lms;
    CourseSchedulingCommandHandler courseSchedulingCommandHandler;
    CommandFactory commandHandler;

    @BeforeEach
    void setUp() {
        lms = new LMS();
        courseSchedulingCommandHandler = new CourseSchedulingCommandHandler(lms);
        commandHandler = CommandFactory.init(courseSchedulingCommandHandler);
    }

    @Test
    void init_shouldInitializeAllCommands() {
        assertTrue(commandHandler.getCommands().keySet().contains(ADD_COURSE_OFFERING_COMMAND));
        assertTrue(commandHandler.getCommands().keySet().contains(REGISTER_COMMAND));
        assertTrue(commandHandler.getCommands().keySet().contains(CANCEL_COMMAND));
        assertTrue(commandHandler.getCommands().keySet().contains(ALLOT_COURSE_COMMAND));
    }

    @Test
    void execute_shouldThrowCommandNotFoundException() {
        assertThrows(CommandNotFoundException.class, () -> commandHandler.executeCommand("UNKNOWN", new String[]{}));
    }

}