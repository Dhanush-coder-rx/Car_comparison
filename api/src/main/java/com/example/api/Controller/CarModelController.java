package com.example.api.Controller;


import com.example.api.Entity.CarBrand;
import com.example.api.Entity.CarModel;

import com.example.api.Entity.CarVariant;
import com.example.api.Repository.CarBrandRepository;
import com.example.api.Repository.CarModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class CarModelController {

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @PostMapping("/createCarModel")
    public CarModel createCarModel(@RequestBody CarModel carModel) {

        return carModelRepository.save(carModel);
    }



    @GetMapping("/getCarModels/{brandName}")
    public ResponseEntity<List<CarModel>> getCarModels(@PathVariable String brandName) {
        Optional<CarBrand> carBrandOptional = carBrandRepository.findById(brandName);
        if (!carBrandOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CarBrand carBrand = carBrandOptional.get();
        List<CarModel> carModels = carModelRepository.findByCarBrandBrandName(brandName);
        return new ResponseEntity<>(carModels, HttpStatus.OK);
    }

    @GetMapping("/getAllCarModels")
    public ResponseEntity<?> getAllCarModels(){
        var CarModel= carModelRepository.findAll();
        return new ResponseEntity<>(CarModel, HttpStatus.OK);
    }

}