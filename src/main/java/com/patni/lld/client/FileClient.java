package com.patni.lld.client;

import com.patni.lld.interaction.CommandFactory;

import java.io.BufferedReader;

public class FileClient extends Client {
    public FileClient(BufferedReader inputReader, CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }
}
