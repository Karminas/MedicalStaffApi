package com.MedicalStaffing.MedicalStaffing.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class Office extends RepresentationModel {
    //Constructor
    public Office() {}
    public Office(Long id, String street1, String street2, Long streetNumber, String city, String province) {
        this.id = id;
        this.street1 = street1;
        this.street2 = street2;
        this.streetNumber = streetNumber;
        City = city;
        Province = province;
    }

    //Attributes
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 3)
    private String street1;

    @NotNull
    @Length(min = 3)
    private String street2;

    @NotNull
    private Long streetNumber;

    private String City;
    private String Province;

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public Long getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Long streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    //ToString
    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", streetNumber=" + streetNumber +
                ", City='" + City + '\'' +
                ", Province='" + Province + '\'' +
                '}';
    }
}
