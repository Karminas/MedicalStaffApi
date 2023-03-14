package com.MedicalStaffing.MedicalStaffing.Exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(Long id) {
        super("Resource not found. Id: " + id);
    }
}
