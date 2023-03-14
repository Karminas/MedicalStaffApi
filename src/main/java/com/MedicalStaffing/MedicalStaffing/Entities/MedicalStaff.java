package com.MedicalStaffing.MedicalStaffing.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Entity
public class MedicalStaff extends RepresentationModel<MedicalStaff> {

    //Constructor
    public MedicalStaff(){}

    public MedicalStaff(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    // Attributes
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 2, max = 15)
    private String name;

    @NotNull
    @Length(min = 2, max = 15)
    private String lastName;

    @ManyToMany (fetch = FetchType.EAGER)
    List<MedicalSpeciality> specialityList;

    //Getters and Setters

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<MedicalSpeciality> getSpecialityList() {
        return specialityList;
    }

    public void setSpecialityList(List<MedicalSpeciality> specialityList) {
        this.specialityList = specialityList;
    }
    //ToString

    @Override
    public String toString() {
        return "MedicalStaff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialityList=" + specialityList +
                '}';
    }
}
