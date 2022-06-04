package com.patni.lld.interaction.impl;

import com.patni.lld.exception.CourseNotFoundException;
import com.patni.lld.exception.InsufficientEmployeePresentException;
import com.patni.lld.exception.InvalidParameterException;
import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.interaction.Command;

public class AllotCourseCommand implements Command {

    private CourseSchedulingCommandHandler courseSchedulingCommandHandler;

    public AllotCourseCommand(CourseSchedulingCommandHandler courseSchedulingCommandHandler) {
        this.courseSchedulingCommandHandler = courseSchedulingCommandHandler;
    }

    @Override
    public String helpText() {
        return "INPUT_DATA_ERROR";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length != 1) {
            throw new InvalidParameterException(helpText());
        }
        try {
            courseSchedulingCommandHandler.allotCourse(params[0]);
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientEmployeePresentException e) {
            System.out.println(e.getMessage());
        }
    }
}
