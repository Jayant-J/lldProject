package com.patni.lld.interaction.commands;

import com.patni.lld.handler.ParkingLotCommandHandler;
import com.patni.lld.exceptions.InvalidParameterException;

public class ParkCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public ParkCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "park <registrationNumber> <color>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if(params.length < 2) {
            throw new InvalidParameterException("Expected two parameters <registrationNumber> and <color>");
        }

        parkingLotCommandHandler.park(params[0], params[1]);
    }
}
