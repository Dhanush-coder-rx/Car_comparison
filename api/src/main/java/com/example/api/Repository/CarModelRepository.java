package com.example.api.Repository;

import com.example.api.Entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, String> {
    List<CarModel> findByCarBrandBrandName(String brandname);



}