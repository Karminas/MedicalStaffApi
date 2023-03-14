package com.MedicalStaffing.MedicalStaffing.Controllers;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalStaff;
import com.MedicalStaffing.MedicalStaffing.Services.MedicalStaffService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "api/v1/medicalStaff")
public class MedicalStaffController {

    // Services
    private final MedicalStaffService medicalStaffService;

    // Constructor
    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    // Methods
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MedicalStaff> getAllMedicalStaffMembers() {
        return medicalStaffService.getAllMedicalStaff();
    }

    @GetMapping (value = "{id}")
    @ResponseStatus (HttpStatus.OK)
    public MedicalStaff getMedicalStaffById (@PathVariable Long id) {
        return medicalStaffService.findMedicalStaffById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewMedicalStaff (@RequestBody @Valid MedicalStaff newMedicalStaff) {
        medicalStaffService.addNewMedicalStaffMember(newMedicalStaff);
    }

    @PostMapping (value = "{id}")
    public void updateMedicalStaffById (@PathVariable Long id,
                                        @RequestBody @Valid MedicalStaff medicalStaff) {
        medicalStaffService.updateMedicalStaffById(id, medicalStaff);
    }

    @DeleteMapping (value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedicalStaff (@PathVariable Long id) {
        medicalStaffService.deleteMedicalStaffById(id);
    }
}
