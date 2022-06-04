package com.patni.lld.interaction;

import com.patni.lld.exception.CommandNotFoundException;
import com.patni.lld.exception.InvalidParameterException;
import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.interaction.impl.AddCourseCommand;
import com.patni.lld.interaction.impl.AllotCourseCommand;
import com.patni.lld.interaction.impl.CancelCommand;
import com.patni.lld.interaction.impl.RegisterCommand;

import java.util.HashMap;
import java.util.Map;

import static com.patni.lld.utils.Constants.*;

public class CommandFactory {
    private Map<String, Command> commands;

    private CommandFactory() {
        commands = new HashMap<>();
    }

    public static CommandFactory init(CourseSchedulingCommandHandler courseSchedulingCommandHandler) {
        final CommandFactory cf = new CommandFactory();

        cf.addCommand(ADD_COURSE_OFFERING_COMMAND, new AddCourseCommand(courseSchedulingCommandHandler));
        cf.addCommand(REGISTER_COMMAND, new RegisterCommand(courseSchedulingCommandHandler));
        cf.addCommand(ALLOT_COURSE_COMMAND, new AllotCourseCommand(courseSchedulingCommandHandler));
        cf.addCommand(CANCEL_COMMAND, new CancelCommand(courseSchedulingCommandHandler));

        return cf;
    }

    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String name, String[] params) throws CommandNotFoundException, InvalidParameterException {
        if (commands.containsKey(name)) {
            commands.get(name).execute(params);
        } else {
            throw new CommandNotFoundException(name);
        }
    }

    public void listCommandHelp() {
        commands.keySet().stream()
                .map(command -> commands.get(command).helpText())
                .forEach(System.out::println);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
