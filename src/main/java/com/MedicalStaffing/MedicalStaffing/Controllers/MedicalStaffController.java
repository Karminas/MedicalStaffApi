package com.MedicalStaffing.MedicalStaffing.Controllers;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalSpeciality;
import com.MedicalStaffing.MedicalStaffing.Entities.MedicalStaff;
import com.MedicalStaffing.MedicalStaffing.Services.MedicalSpecialityService;
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
    private final MedicalSpecialityService medicalSpecialityService;

    // Constructor
    public MedicalStaffController(MedicalStaffService medicalStaffService, MedicalSpecialityService medicalSpecialityService) {
        this.medicalStaffService = medicalStaffService;
        this.medicalSpecialityService = medicalSpecialityService;
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

                for (MedicalSpeciality speciality:medicalStaff.getSpecialityList()) {
                    speciality.add(linkTo(methodOn(MedicalSpecialityController.class).getMedicalSpecialityById(speciality.getId())).withSelfRel());
                    speciality.add(linkTo(methodOn(MedicalSpecialityController.class).getAllMedicalSpecialities()).withRel(IanaLinkRelations.COLLECTION));
                }
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
        MedicalStaff medicalStaffId = medicalStaffService.updateMedicalStaffById(id, medicalStaff);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping (value = "{id}")
    @Operation(summary = "Delete one specified Medical Staff by id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedicalStaff (@PathVariable Long id) {
        medicalStaffService.deleteMedicalStaffById(id);
    }

    @GetMapping (value = "{id}/medicalSpecialities")
    @Operation(summary = "Get selected Medical Staff member by id's list of specialities")
    public ResponseEntity<CollectionModel<MedicalSpeciality>> getAllMedicalSpecialitiesForMedicalStaffMember (@PathVariable Long id) {
        MedicalStaff foundMedicalStaff = medicalStaffService.findMedicalStaffById(id);

        if (foundMedicalStaff!= null) {
            for (MedicalSpeciality medicalSpeciality: foundMedicalStaff.getSpecialityList()) {
                medicalSpeciality.add(linkTo(methodOn(MedicalSpecialityController.class).getMedicalSpecialityById(medicalSpeciality.getId())).withSelfRel());
            }
            CollectionModel<MedicalSpeciality> collectionModel = CollectionModel.of(foundMedicalStaff.getSpecialityList());

            collectionModel.add(linkTo(methodOn(MedicalStaffController.class).getMedicalStaffById(foundMedicalStaff.getId())).withRel("Medical Staff"));
            collectionModel.add(linkTo(methodOn(MedicalSpecialityController.class).getAllMedicalSpecialities()).withRel(IanaLinkRelations.COLLECTION));
            collectionModel.add(linkTo(methodOn(MedicalStaffController.class).getAllMedicalStaffMembers()).withRel(IanaLinkRelations.COLLECTION));
            return new ResponseEntity<>(collectionModel, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping (value = "{medicalStaffId}/medicalSpecialities/{medicalSpecialityId}")
    @Operation(summary = "Add one medical speciality to a Medical Staff member")
    public ResponseEntity<MedicalStaff> addMedicalSpecialityToMedicalStaff (@PathVariable Long medicalStaffId,
                                                                            @PathVariable Long medicalSpecialityId) {
        MedicalStaff foundMedicalStaff = medicalStaffService.findMedicalStaffById(medicalStaffId);
        MedicalSpeciality foundMedicalSpeciality = medicalSpecialityService.getMedicalSpecialityById(medicalSpecialityId);

        if (foundMedicalStaff != null && foundMedicalSpeciality != null) {
            foundMedicalStaff.getSpecialityList().add(foundMedicalSpeciality);
            MedicalStaff updateMedicalStaff = medicalStaffService.updateMedicalStaffById(medicalStaffId, foundMedicalStaff);
            updateMedicalStaff.add(linkTo(methodOn(MedicalStaffController.class).getMedicalStaffById(updateMedicalStaff.getId())).withSelfRel());
            updateMedicalStaff.add(linkTo(methodOn(MedicalStaffController.class).getAllMedicalStaffMembers()).withRel(IanaLinkRelations.COLLECTION));
            return new ResponseEntity<>(updateMedicalStaff, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
