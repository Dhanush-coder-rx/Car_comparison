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
public class CarVariant {
    @Id
    private String variantName; // Primary key for car variants

    private String varianttype;

    private String variantsubtype;

    private String price;

    @ManyToOne
    private CarModel carModel; // Foreign key to CarModel



    @OneToMany(mappedBy = "carVariant")
    @JsonIgnore
    List<Specifications> specificationList;


}
