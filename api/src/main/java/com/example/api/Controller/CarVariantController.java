package com.example.api.Controller;

import com.example.api.Entity.CarBrand;
import com.example.api.Entity.CarModel;
import com.example.api.Entity.CarVariant;
import com.example.api.Repository.CarModelRepository;
import com.example.api.Repository.CarVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CarVariantController {

    @Autowired
    private CarVariantRepository carVariantRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @PostMapping("/createCarVariant/{modelName}")
    public CarVariant createCarVariant(@RequestBody CarVariant carVariant,@PathVariable String modelName) {
        CarModel carModel=carModelRepository.findById(modelName).orElseThrow(() -> new RuntimeException("User not found"));
        carVariant.setCarModel(carModel);
        return carVariantRepository.save(carVariant);
    }




    @GetMapping("/getCarVariants/{modelName}")
    public  ResponseEntity<List<CarVariant>> getCarVariants(@PathVariable String modelName){
        Optional<CarModel> carModelOptional=carModelRepository.findById(modelName);
        if (!carModelOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CarModel carModel= carModelOptional.get();
        List<CarVariant> carVariants=carVariantRepository.findByCarModelModelName(modelName);
        return new ResponseEntity<>(carVariants,HttpStatus.OK);
    }
    @GetMapping("/getAllCarVariants")
    public ResponseEntity<?> getAllCarVariants(){
        var CarVariant= carVariantRepository.findAll();
        return new ResponseEntity<>(CarVariant, HttpStatus.OK);
    }


}