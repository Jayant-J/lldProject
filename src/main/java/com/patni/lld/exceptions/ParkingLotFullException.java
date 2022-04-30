package com.patni.lld.exceptions;

import static com.patni.lld.utils.MessageConstants.PARKING_LOT_FULL_MSG;

public class ParkingLotFullException extends RuntimeException {
    @Override
    public String getMessage() {
        return PARKING_LOT_FULL_MSG;
    }
}
