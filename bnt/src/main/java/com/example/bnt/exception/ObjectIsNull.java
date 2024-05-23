package com.example.bnt.exception;

public class ObjectIsNull extends Throwable {
    String excepMassege = "Null";

    public ObjectIsNull(String excepMassage) {
        this.excepMassege = excepMassage;
    }
}
