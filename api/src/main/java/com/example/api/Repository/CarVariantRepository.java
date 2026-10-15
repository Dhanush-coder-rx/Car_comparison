package com.example.api.Repository;

import com.example.api.Entity.CarVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarVariantRepository extends JpaRepository<CarVariant, String> {
    List<CarVariant> findByCarModelModelName(String modelName);
}