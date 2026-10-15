package com.example.api.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CarBrand {
    @Id
    private String brandName; // Primary key for car brands


    @Lob
    @Column(name = "logo", columnDefinition = "LONGBLOB") // Change to LONGBLOB
    private byte[] logo; // Field to store the logo as a byte array

    @OneToMany(mappedBy = "carBrand")
    @JsonIgnore
    List<CarModel> modelList;
}
