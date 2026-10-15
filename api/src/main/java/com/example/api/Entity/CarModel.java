package com.example.api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CarModel {
    @Id
    private String modelName; // Primary key for car models

    private String year;

    @ManyToOne
    private CarBrand carBrand; // Foreign key to CarBrand

    @OneToMany(mappedBy = "carModel")
    @JsonIgnore
    List<CarVariant> variantList;

}
