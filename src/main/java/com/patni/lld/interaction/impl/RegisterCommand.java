package com.patni.lld.interaction.impl;

import com.patni.lld.exception.BatchFullException;
import com.patni.lld.exception.CourseNotFoundException;
import com.patni.lld.exception.EmployeeAlreadyRegisteredException;
import com.patni.lld.exception.InvalidParameterException;
import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.interaction.Command;

public class RegisterCommand implements Command {
    private CourseSchedulingCommandHandler courseSchedulingCommandHandler;

    public RegisterCommand(CourseSchedulingCommandHandler courseSchedulingCommandHandler) {
        this.courseSchedulingCommandHandler = courseSchedulingCommandHandler;
    }

    @Override
    public String helpText() {
        return "INPUT_DATA_ERROR";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length != 2) {
            throw new InvalidParameterException(helpText());
        }
        try {
            courseSchedulingCommandHandler.registerForCourse(params[0], params[1]);
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (EmployeeAlreadyRegisteredException e) {
            System.out.println(e.getMessage());
        } catch (BatchFullException e) {
            System.out.println(e.getMessage());
        }
    }
}
