package com.CarPartStoreERPBackend.carPart.control;

import com.CarPartStoreERPBackend.carPart.entity.CarPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarPartRepository extends JpaRepository<CarPartEntity, Long> {
    Optional<CarPartEntity> findByName(String name);
}
