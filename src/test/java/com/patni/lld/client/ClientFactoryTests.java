package com.patni.lld.client;

import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.interaction.CommandFactory;
import com.patni.lld.model.LMS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientFactoryTests {
    LMS lms;
    CommandFactory commandFactory;

    @BeforeEach
    public void commandFactory() {
        lms = new LMS();
        CourseSchedulingCommandHandler courseSchedulingCommandHandler = new CourseSchedulingCommandHandler(lms);
        commandFactory = CommandFactory.init(courseSchedulingCommandHandler);
    }

    @Test
    public void buildClientWithNoArg_shouldCreateCLIClient() throws FileNotFoundException {
        String[] args = {};
        Client client = ClientFactory.buildClient(args, commandFactory);

        assertTrue(client instanceof CLIClient);
    }

    @Test
    public void buildClientWithValidFilePath_shouldCreateFileClient() throws FileNotFoundException {
        String[] args = {"sample_input/input1.txt"};
        Client client = ClientFactory.buildClient(args, commandFactory);

        assertTrue(client instanceof FileClient);
    }

    @Test
    public void buildClientWithInvalidFilePath_shouldThrowError() throws FileNotFoundException {
        String[] args = {"invalid_file_path.txt"};
        assertThrows(FileNotFoundException.class, () -> ClientFactory.buildClient(args, commandFactory));
    }

}
