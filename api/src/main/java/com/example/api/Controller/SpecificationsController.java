package com.example.api.Controller;


import com.example.api.Entity.CarVariant;
import com.example.api.Entity.Specifications;
import com.example.api.Repository.CarVariantRepository;
import com.example.api.Repository.SpecificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class SpecificationsController {

    @Autowired
    private SpecificationsRepository specificationsRepository;

    @Autowired
    private CarVariantRepository carVariantRepository;

    @PostMapping("/createSpecifications/{variantname}")
    public Specifications createSpecifications(@RequestBody Specifications specifications, @PathVariable String variantname) {
        CarVariant carVariant=carVariantRepository.findById(variantname).orElseThrow(() -> new RuntimeException("User not found"));
        specifications.setCarVariant(carVariant);
        return specificationsRepository.save(specifications);
    }




    @GetMapping("/getSpecifications")
    public ResponseEntity<?> getAllEngineAndTransmission(){
        var EngineAndTransmission= specificationsRepository.findAll();
        return new ResponseEntity<>(EngineAndTransmission, HttpStatus.OK);
    }
    @GetMapping("/getSpecifications/{variantname}")
    public  ResponseEntity<List<Specifications>> getSpecifications(@PathVariable String variantname){
        Optional<CarVariant> carVariantOptional=carVariantRepository.findById(variantname);
        if (!carVariantOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CarVariant carVariant= carVariantOptional.get();
        List<Specifications> specifications = specificationsRepository.findByCarVariantVariantName(variantname);
        return new ResponseEntity<>(specifications,HttpStatus.OK);
    }

}