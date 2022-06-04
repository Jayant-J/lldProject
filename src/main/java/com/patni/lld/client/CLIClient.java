package com.patni.lld.client;

import com.patni.lld.interaction.CommandFactory;

import java.io.BufferedReader;

public class CLIClient extends Client {
    public CLIClient(BufferedReader inputReader, CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }
}
