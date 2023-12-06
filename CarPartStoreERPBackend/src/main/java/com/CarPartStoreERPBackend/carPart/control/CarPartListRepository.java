package com.CarPartStoreERPBackend.carPart.control;

import com.CarPartStoreERPBackend.carPart.entity.CarPartListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPartListRepository extends JpaRepository<CarPartListEntity, Long> {
}
