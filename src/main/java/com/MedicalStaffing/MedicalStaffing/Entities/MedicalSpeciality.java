package com.MedicalStaffing.MedicalStaffing.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class MedicalSpeciality extends RepresentationModel<MedicalSpeciality>{

    //Constructors
    public MedicalSpeciality() {
    }

    public MedicalSpeciality(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //ToString
    @Override
    public String toString() {
        return "MedicalSpeciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
