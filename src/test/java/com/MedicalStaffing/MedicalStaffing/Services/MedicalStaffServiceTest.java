package com.MedicalStaffing.MedicalStaffing.Services;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalStaff;
import com.MedicalStaffing.MedicalStaffing.Repositories.MedicalStaffRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalStaffServiceTest {

    @Mock
    private MedicalStaffRepository medicalStaffRepository;
    @InjectMocks
    private MedicalStaffService medicalStaffService;
    private List<MedicalStaff> medicalStaffs = new ArrayList<>();

    @BeforeEach
    void beforeAll() {
        MedicalStaff medicalStaff1 = new MedicalStaff(1l, "Tupac","Ocampo");
        MedicalStaff medicalStaff2 = new MedicalStaff(2l, "Alejandro", "Orozco");
        medicalStaffs.add(medicalStaff1);
        medicalStaffs.add(medicalStaff2);
    }

    @AfterEach
    void afterAll() {
        medicalStaffs.clear();
    }

    @Test
    void getAllMedicalStaff() {
        //given
        List<MedicalStaff> returnList;
        when(medicalStaffRepository.findAll()).thenReturn(medicalStaffs);
        //When
        returnList = medicalStaffService.getAllMedicalStaff();
        //Then
        verify(medicalStaffRepository).findAll();
        assertEquals(2, returnList.stream().count());
    }

    @Test
    void findMedicalStaffById() {
        //When
        when(medicalStaffRepository.findById(1l)).thenReturn(medicalStaffs.stream().filter(medicalStaff -> medicalStaff.getId().equals(1l)).findFirst());
        //Then
        MedicalStaff foundMedicalStaff = medicalStaffService.findMedicalStaffById(1l);
        assertEquals(1l, foundMedicalStaff.getId());
        verify(medicalStaffRepository).findById(1l);
    }

    @Test
    void addNewMedicalStaffMember() {
        //When
        MedicalStaff newMedicalStaff = new MedicalStaff(3l, "NewName", "NewLastName");
        when(medicalStaffRepository.save(newMedicalStaff)).thenReturn(newMedicalStaff);
        //Given
        MedicalStaff addedMedicalStaff = medicalStaffService.addNewMedicalStaffMember(newMedicalStaff);
        //Then
        verify(medicalStaffRepository).save(newMedicalStaff);
        assertEquals(newMedicalStaff, addedMedicalStaff);
    }

    @Test
    void updateMedicalStaffById() {
    }

    @Test
    void deleteMedicalStaffById() {
        //When
        medicalStaffService.deleteMedicalStaffById(1l);
        //Then
        verify(medicalStaffRepository).deleteById(1l);
    }
}