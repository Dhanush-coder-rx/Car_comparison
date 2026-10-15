package com.example.api.Controller;

import com.example.api.Entity.CarBrand;
import com.example.api.Entity.CarModel;
import com.example.api.Entity.CarVariant;
import com.example.api.Repository.CarBrandRepository;
import com.example.api.Repository.CarModelRepository;
import com.example.api.Repository.CarVariantRepository;
import com.example.api.Repository.SpecificationsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")

public class CarBrandController {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarVariantRepository carVariantRepository;

    @Autowired
    private SpecificationsRepository specificationsRepository;

//    @PostMapping("/createCarBrand")
//    public CarBrand createCarBrand(@RequestBody  CarBrand carBrand) {
//        return carBrandRepository.save(carBrand);
//
//    }

    @PostMapping("/createCarBrand")
    public ResponseEntity<String> createCarBrand(
            @RequestParam("brandName") String bankName,
            @RequestParam("logo") MultipartFile logo) {
        try {
            if (logo.isEmpty()) {
                return ResponseEntity.badRequest().body("Logo file is empty.");
            }

            byte[] logoBytes = logo.getBytes();
            CarBrand carBrand = new CarBrand();
            carBrand.setBrandName(bankName);
            carBrand.setLogo(logoBytes);

            carBrandRepository.save(carBrand);

            return ResponseEntity.ok("Bank created successfully");
        } catch (Exception e) {
            // Log the error (use a logger in production)
            System.err.println("Error creating car brand: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating Bank: " + e.getMessage());
        }
    }
    @GetMapping("/GetCaBrand")
    public ResponseEntity<?> GetCarBrand() {
        var CarBrand = carBrandRepository.findAll();
        return new ResponseEntity<>(CarBrand, HttpStatus.OK);
    }

    @GetMapping("/getCarBrandById/{brandName}")
    public ResponseEntity<?> getCarBrandById(@PathVariable("brandName") String brandName) {
        Optional<CarBrand> carBrandOp = carBrandRepository.findById(brandName);

        if (carBrandOp.isPresent()) {
            CarBrand carBrand = carBrandOp.get();
            List<CarModel> carModels = carModelRepository.findByCarBrandBrandName(brandName);

            if (carModels != null && !carModels.isEmpty()) {
                // Collecting all car variants for all models
                List<CarVariant> carVariants = new ArrayList<>();

                for (CarModel carModel : carModels) {
                    String modelName = carModel.getModelName();
                    List<CarVariant> variantsForModel = carVariantRepository.findByCarModelModelName(modelName);

                    if (variantsForModel != null) {
                        carVariants.addAll(variantsForModel);
                    }
                }

                if (!carVariants.isEmpty()) {
                    return new ResponseEntity<>(carVariants, HttpStatus.OK);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car variants found.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car models found for the given car brand.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CarBrand not found.");
        }
    }

}

