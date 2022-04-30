package com.patni.lld;

import com.patni.lld.client.Client;
import com.patni.lld.client.ClientFactory;
import com.patni.lld.handler.ParkingLotCommandHandler;
import com.patni.lld.interaction.CommandFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        CommandFactory commandFactory = CommandFactory.init(parkingLotCommandHandler);

        try {
            Client client = ClientFactory.buildClient(args, commandFactory);
            client.handleInput();
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry! the supplied input file was not found!");
        } catch (IOException ex) {
            System.out.println("Something went wrong. Please try again!");
        }
    }
}