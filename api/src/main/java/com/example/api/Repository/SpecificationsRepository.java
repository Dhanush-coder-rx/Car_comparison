package com.example.api.Repository;

import com.example.api.Entity.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationsRepository extends JpaRepository<Specifications, Long> {
    List<Specifications> findByCarVariantVariantName(String variantName);
}