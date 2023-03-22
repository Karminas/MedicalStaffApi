package com.MedicalStaffing.MedicalStaffing.Services;

import com.MedicalStaffing.MedicalStaffing.Repositories.MedicalSpecialityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MedicalSpecialityServiceTest {

    @Mock
    private MedicalSpecialityRepository medicalSpecialityRepositoryTest;
    @InjectMocks
    private MedicalSpecialityService medicalSpecialityServiceTest;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        medicalSpecialityServiceTest = new MedicalSpecialityService(medicalSpecialityRepositoryTest);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void getAllMedicalSpecialities() {
        //When
        medicalSpecialityServiceTest.getAllMedicalSpecialities();
        //Then
        verify(medicalSpecialityRepositoryTest).findAll();
    }

    @Test
    void getMedicalSpecialityById() {
        //When
        medicalSpecialityServiceTest.getMedicalSpecialityById(1l);
        //Then
        verify(medicalSpecialityRepositoryTest).findById(1l);
    }

    @Test
    void editMedicalSpecialityById() {
    }

    @Test
    void deleteMedicalSpecialityById() {
    }
}