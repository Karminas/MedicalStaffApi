package com.MedicalStaffing.MedicalStaffing.Repositories;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long> {
}
