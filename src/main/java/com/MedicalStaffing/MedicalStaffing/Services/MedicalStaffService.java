package com.MedicalStaffing.MedicalStaffing.Services;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalStaff;
import com.MedicalStaffing.MedicalStaffing.Exceptions.MedicalStaffNotFoundException;
import com.MedicalStaffing.MedicalStaffing.Repositories.MedicalStaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffService {

    // Repositories
    private final MedicalStaffRepository medicalStaffRepository;

    // Constructor
    public MedicalStaffService(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }

    //Methods
    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffRepository.findAll();
    }

    public MedicalStaff findMedicalStaffById (Long id) {
        return medicalStaffRepository.findById(id).orElseThrow(()-> new MedicalStaffNotFoundException(id));
    }

    public MedicalStaff addNewMedicalStaffMember (MedicalStaff newMedicalStaff) {
        MedicalStaff savedMedicalStaff = medicalStaffRepository.save(newMedicalStaff);
        return savedMedicalStaff;
    }

    public Long updateMedicalStaffById (Long id, MedicalStaff update_medicalStaff) {
        MedicalStaff foundMedicalStaff = medicalStaffRepository.findById(id).orElseThrow(()-> new RuntimeException("This medical staff does not exists."));
        foundMedicalStaff.setName(update_medicalStaff.getName());
        foundMedicalStaff.setLastName(update_medicalStaff.getLastName());
        medicalStaffRepository.save(foundMedicalStaff);
        return foundMedicalStaff.getId();
    }

    public void deleteMedicalStaffById(Long id) {
        medicalStaffRepository.deleteById(id);
    }
}
