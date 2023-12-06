package com.CarPartStoreERPBackend.carPart.control;

import com.CarPartStoreERPBackend.carPart.entity.CarPartEntity;
import com.CarPartStoreERPBackend.carPart.entity.CarPartType;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarPartService {
    private final CarPartRepository carPartRepository;

    public List<CarPartEntity> getAllCarParts() {
        return carPartRepository.findAll();
    }

    public CarPartEntity getCarPartById(Long partId) {
        return carPartRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("not found by given id " + partId));
    }
    @Transactional
    public CarPartEntity updateCarPartById(Long partId, CarPartEntity newCartPartEntity) {
        CarPartEntity persisted = getCarPartById(partId);

        persisted.setCarPartType(newCartPartEntity.getCarPartType());
        persisted.setName(newCartPartEntity.getName());
        persisted.setCarModel(newCartPartEntity.getCarModel());
        persisted.setCarMarke(newCartPartEntity.getCarMarke());
        persisted.setPrice(newCartPartEntity.getPrice());
        persisted.setRabatt(newCartPartEntity.getRabatt());
        persisted.setCarPartType(newCartPartEntity.getCarPartType());
        persisted.setBarCode(newCartPartEntity.getBarCode());
        persisted.setVolumeInCube(newCartPartEntity.getVolumeInCube());
        persisted.setTotalWeightInKg(newCartPartEntity.getTotalWeightInKg());
        persisted.setQuantity(newCartPartEntity.getQuantity());
        persisted.setQuantityLimit(newCartPartEntity.getQuantityLimit());
        persisted.setPackageCount(newCartPartEntity.getPackageCount());
        return carPartRepository.saveAndFlush(persisted);
    }
    @Transactional
    public List<CarPartEntity> orderParts(List<CarPartEntity> partsToOrder){
        return partsToOrder.stream().map(partToOrder -> updateCarPartById(partToOrder.getId(), partToOrder)).toList();
    }

    @Transactional
    public void deleteCarPartById(Long partId) {
        carPartRepository.deleteById(partId);
    }

    public CarPartEntity addCarPart(CarPartEntity newCartPartEntity) {
        Optional<CarPartEntity> persisted = carPartRepository.findByName(newCartPartEntity.getName());
        if (persisted.isEmpty()){
            newCartPartEntity.setBarCode(java.util.UUID.randomUUID().toString());
            newCartPartEntity.setCarPartList(null);
            return carPartRepository.save(newCartPartEntity);
        }
        else throw new RuntimeException("The given Name is already exists" + newCartPartEntity.getName());

    }

}
