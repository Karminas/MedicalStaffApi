package com.MedicalStaffing.MedicalStaffing.Exceptions;

public class MedicalStaffNotFoundException extends RuntimeException{

    public MedicalStaffNotFoundException(Long id) {
        super("Resource not found. Id: " + id);
    }
}
