package com.patni.lld.interaction.commands;

import com.patni.lld.exceptions.InvalidParameterException;

public interface Command {
    String helpText();
    void execute(String[] params) throws InvalidParameterException;
}
