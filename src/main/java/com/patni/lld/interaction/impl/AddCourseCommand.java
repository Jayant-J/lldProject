package com.patni.lld.interaction.impl;

import com.patni.lld.exception.InvalidEmployeeCountProvidedException;
import com.patni.lld.exception.InvalidParameterException;
import com.patni.lld.handler.CourseSchedulingCommandHandler;
import com.patni.lld.interaction.Command;

import java.time.LocalDate;

public class AddCourseCommand implements Command {
    private CourseSchedulingCommandHandler courseSchedulingCommandHandler;

    public AddCourseCommand(CourseSchedulingCommandHandler courseSchedulingCommandHandler) {
        this.courseSchedulingCommandHandler = courseSchedulingCommandHandler;
    }

    @Override
    public String helpText() {
        return "INPUT_DATA_ERROR";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length != 5) {
            throw new InvalidParameterException(helpText());
        }
        try {
            int year = Integer.parseInt(params[2].substring(4, 8));
            int month = Integer.parseInt(params[2].substring(2, 4));
            int day = Integer.parseInt(params[2].substring(0, 2));
            LocalDate courseDate = LocalDate.of(year, month, day);
            int minEmployee = Integer.parseInt(params[3]);
            int maxEmployee = Integer.parseInt(params[4]);
            courseSchedulingCommandHandler.addCourse(params[0], params[1], courseDate, minEmployee, maxEmployee);
        } catch (InvalidEmployeeCountProvidedException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(helpText());
        }
    }
}
