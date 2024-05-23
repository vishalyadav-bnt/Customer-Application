package com.example.bnt.exception;

public class DataIsNotPresent extends Exception {
    String excepMassege = "Empty";

    public DataIsNotPresent(String excepMassage) {
        this.excepMassege = excepMassage;
    }
}
