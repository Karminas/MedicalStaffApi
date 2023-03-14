package com.MedicalStaffing.MedicalStaffing.Repositories;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalSpecialityRepository extends JpaRepository <MedicalSpeciality, Long> {

}
