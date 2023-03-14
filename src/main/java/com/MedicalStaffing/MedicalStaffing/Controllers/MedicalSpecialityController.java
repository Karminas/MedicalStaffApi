package com.MedicalStaffing.MedicalStaffing.Controllers;

import com.MedicalStaffing.MedicalStaffing.Entities.MedicalSpeciality;
import com.MedicalStaffing.MedicalStaffing.Services.MedicalSpecialityService;
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
@RequestMapping(value = "api/v1/medicalSpeciality")
public class MedicalSpecialityController {

    //Service provider instances
    private final MedicalSpecialityService medicalSpecialityService;

    // Constructor
    public MedicalSpecialityController(MedicalSpecialityService medicalSpecialityService) {
        this.medicalSpecialityService = medicalSpecialityService;
    }

    //Methods
    @GetMapping
    public ResponseEntity<CollectionModel<MedicalSpeciality>> getAllMedicalSpecialities () {
        List<MedicalSpeciality> allMedicalSpecialities =  medicalSpecialityService.getAllMedicalSpecialities();

        for (MedicalSpeciality medicalSpeciality: allMedicalSpecialities) {
            medicalSpeciality.add(linkTo(methodOn(MedicalSpecialityController.class).getMedicalSpecialityById(medicalSpeciality.getId())).withSelfRel());
        }

        CollectionModel<MedicalSpeciality> collection = CollectionModel.of(allMedicalSpecialities);
        collection.add(linkTo(methodOn(MedicalSpecialityController.class).getAllMedicalSpecialities()).withRel(IanaLinkRelations.COLLECTION));
        collection.add(linkTo(methodOn(MedicalStaffController.class).getAllMedicalStaffMembers()).withRel(IanaLinkRelations.COLLECTION));
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @GetMapping (value = "{id}")
    public ResponseEntity<MedicalSpeciality> getMedicalSpecialityById (@PathVariable Long id) {
        MedicalSpeciality foundMedicalSpeciality = medicalSpecialityService.getMedicalSpecialityById(id);

        if (foundMedicalSpeciality!=null) {
            return new ResponseEntity<>(createRepresentationModel(foundMedicalSpeciality), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<MedicalSpeciality> addNewMedicalSpeciality (@RequestBody @Valid MedicalSpeciality medicalSpeciality) {
        MedicalSpeciality addedMedicalSpeciality = medicalSpecialityService.addNewMedicalSpeciality(medicalSpeciality);
        if (addedMedicalSpeciality != null) {
            addedMedicalSpeciality.add(linkTo(methodOn(MedicalSpecialityController.class).getMedicalSpecialityById(addedMedicalSpeciality.getId())).withSelfRel());
            addedMedicalSpeciality.add(linkTo(methodOn(MedicalSpecialityController.class).getAllMedicalSpecialities()).withRel(IanaLinkRelations.COLLECTION));
            return new ResponseEntity<>(addedMedicalSpeciality, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping (value = "{id}")
    public ResponseEntity<MedicalSpeciality> editMedicalSpecialityById (@PathVariable Long id,
                                                                        @RequestBody @Valid MedicalSpeciality medicalSpeciality) {
        MedicalSpeciality editedMedicalSpeciality = medicalSpecialityService.editMedicalSpecialityById(id, medicalSpeciality);
        if (editedMedicalSpeciality != null) {
            editedMedicalSpeciality.add(linkTo(methodOn(MedicalSpecialityController.class).getMedicalSpecialityById(editedMedicalSpeciality.getId())).withSelfRel());
            editedMedicalSpeciality.add(linkTo(methodOn(MedicalSpecialityController.class).getAllMedicalSpecialities()).withRel(IanaLinkRelations.COLLECTION));
            return new ResponseEntity<>(editedMedicalSpeciality, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (value = "{id}")
    public ResponseEntity<MedicalSpeciality> deleteMedicalSpeciality (@PathVariable Long id) {
        if (medicalSpecialityService.deleteMedicalSpecialityById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private MedicalSpeciality createRepresentationModel (MedicalSpeciality medicalSpeciality) {
        medicalSpeciality.add(linkTo(methodOn(MedicalSpecialityController.class).getMedicalSpecialityById(medicalSpeciality.getId())).withSelfRel());
        medicalSpeciality.add(linkTo(methodOn(MedicalSpecialityController.class).getAllMedicalSpecialities()).withRel(IanaLinkRelations.COLLECTION));
        return medicalSpeciality;
    }
}
