package com.patni.lld.interaction.commands;

import com.patni.lld.handler.ParkingLotCommandHandler;
import com.patni.lld.exceptions.InvalidParameterException;

public class StatusCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public StatusCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "status";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        this.parkingLotCommandHandler.status();
    }
}
