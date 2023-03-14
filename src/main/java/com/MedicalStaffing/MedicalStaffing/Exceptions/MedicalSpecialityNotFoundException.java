package com.MedicalStaffing.MedicalStaffing.Exceptions;

public class MedicalSpecialityNotFoundException extends RuntimeException{
    public MedicalSpecialityNotFoundException(Long id) {
        super("Medical Speciality not found. Id: " + id);
    }
}
