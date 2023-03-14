package com.MedicalStaffing.MedicalStaffing.Controllers;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalStaff;
import com.MedicalStaffing.MedicalStaffing.Services.MedicalStaffService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    @Operation(summary = "Retrieve all Medical staff members")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CollectionModel<MedicalStaff>> getAllMedicalStaffMembers() {

        List<MedicalStaff> allMedicalStaff = medicalStaffService.getAllMedicalStaff();

        if (!allMedicalStaff.isEmpty()) {
            for (MedicalStaff medicalStaff : allMedicalStaff) {
                medicalStaff.add(linkTo(methodOn(MedicalStaffController.class).getMedicalStaffById(medicalStaff.getId())).withSelfRel());
            }

            CollectionModel<MedicalStaff> collection = CollectionModel.of(allMedicalStaff);
            collection.add(linkTo(methodOn(MedicalStaffController.class).getAllMedicalStaffMembers()).withRel(IanaLinkRelations.COLLECTION));

            return new ResponseEntity<>(collection, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping (value = "{id}")
    @Operation(summary = "Retrieve one specified Medical Staff searching by id")
    @ResponseStatus (HttpStatus.OK)
    public ResponseEntity<MedicalStaff> getMedicalStaffById (@PathVariable Long id) {
        MedicalStaff medicalStaff = medicalStaffService.findMedicalStaffById(id);
        if (medicalStaff != null) {
            medicalStaff.add(linkTo(methodOn(MedicalStaffController.class).getMedicalStaffById(medicalStaff.getId())).withSelfRel());
            medicalStaff.add(linkTo(methodOn(MedicalStaffController.class).getAllMedicalStaffMembers()).withRel(IanaLinkRelations.COLLECTION));
            return new ResponseEntity<>(medicalStaff, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new Medical Staff")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MedicalStaff> addNewMedicalStaff (@RequestBody @Valid MedicalStaff newMedicalStaff) {
        MedicalStaff addedMedicalStaff = medicalStaffService.addNewMedicalStaffMember(newMedicalStaff);

        addedMedicalStaff.add(linkTo(methodOn(MedicalStaffController.class).getMedicalStaffById(addedMedicalStaff.getId())).withSelfRel());
        addedMedicalStaff.add(linkTo(methodOn(MedicalStaffController.class).getAllMedicalStaffMembers()).withRel(IanaLinkRelations.COLLECTION));

        return new ResponseEntity<>(addedMedicalStaff, HttpStatus.CREATED);
    }


    @PostMapping (value = "{id}")
    @Operation(summary = "Update one specified Medical Staff by id")
    public ResponseEntity<?> updateMedicalStaffById (@PathVariable Long id,
                                                     @RequestBody @Valid MedicalStaff medicalStaff) {
        Long medicalStaffId = medicalStaffService.updateMedicalStaffById(id, medicalStaff);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping (value = "{id}")
    @Operation(summary = "Delete one specified Medical Staff by id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedicalStaff (@PathVariable Long id) {
        medicalStaffService.deleteMedicalStaffById(id);
    }
}
