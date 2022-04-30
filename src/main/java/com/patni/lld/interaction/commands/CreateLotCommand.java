package com.patni.lld.interaction.commands;


import com.patni.lld.handler.ParkingLotCommandHandler;
import com.patni.lld.exceptions.InvalidParameterException;
import com.patni.lld.utils.StringUtils;

public class CreateLotCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public CreateLotCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
       return  "create_parking_lot <numSlots>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if(params.length < 1) {
            throw new InvalidParameterException("Expected one parameter <numSlots>");
        }

        if(!StringUtils.isInteger(params[0])) {
            throw new InvalidParameterException("numSlots must be an integer");
        }

        int numSlots = Integer.parseInt(params[0]);
        this.parkingLotCommandHandler.createParkingLot(numSlots);
    }
}
