package com.MedicalStaffing.MedicalStaffing.Services;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalSpeciality;
import com.MedicalStaffing.MedicalStaffing.Exceptions.MedicalStaffNotFoundException;
import com.MedicalStaffing.MedicalStaffing.Exceptions.MedicalSpecialityNotFoundException;
import com.MedicalStaffing.MedicalStaffing.Repositories.MedicalSpecialityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalSpecialityService {

    //Repositories
    private final MedicalSpecialityRepository medicalSpecialityRepository;

    //Constructor
    public MedicalSpecialityService(MedicalSpecialityRepository medicalSpecialityRepository) {
        this.medicalSpecialityRepository = medicalSpecialityRepository;
    }

    public List<MedicalSpeciality> getAllMedicalSpecialities (){
        return medicalSpecialityRepository.findAll();
    }

    public MedicalSpeciality getMedicalSpecialityById (Long id) {
        return medicalSpecialityRepository.findById(id).orElseThrow(()-> new MedicalStaffNotFoundException(id)) ;
    }

    public MedicalSpeciality addNewMedicalSpeciality(MedicalSpeciality medicalSpeciality) {
        return medicalSpecialityRepository.save(medicalSpeciality);
    }

    public MedicalSpeciality editMedicalSpecialityById (Long id, MedicalSpeciality medicalSpeciality) {
        MedicalSpeciality medicalSpecialityEdit = medicalSpecialityRepository.findById(id).orElseThrow(()-> new MedicalSpecialityNotFoundException(id));
        medicalSpecialityEdit.setName(medicalSpeciality.getName());
        medicalSpecialityEdit.setDescription(medicalSpeciality.getDescription());
        return medicalSpecialityRepository.save(medicalSpecialityEdit);
    }
    public boolean deleteMedicalSpecialityById (Long id) {
        medicalSpecialityRepository.deleteById(id);
        if (medicalSpecialityRepository.findById(id).isPresent()) {
            return false;
        }
        return true;
    }
}
