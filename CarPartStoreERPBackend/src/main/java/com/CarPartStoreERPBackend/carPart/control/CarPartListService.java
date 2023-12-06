package com.CarPartStoreERPBackend.carPart.control;

import com.CarPartStoreERPBackend.carPart.entity.CarPartListEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarPartListService {
    private final CarPartListRepository carPartListRepository;

    public List<CarPartListEntity> getAllCarPartLists() {
        return carPartListRepository.findAll();
    }

    public CarPartListEntity getCarPartListById(Long carPartListId) {
        return carPartListRepository.findById(carPartListId)
                .orElseThrow(() -> new RuntimeException("not found by given id " + carPartListId));
    }

    @Transactional
    public CarPartListEntity updateCarPartListById(Long carPartListId, CarPartListEntity carPartListEntity) {
        CarPartListEntity persisted = getCarPartListById(carPartListId);

        persisted.setName(carPartListEntity.getName());
        persisted.setBarCode(carPartListEntity.getBarCode());
        return carPartListRepository.saveAndFlush(persisted);
    }

    @Transactional
    public void deleteCarPartListById(Long carPartListId) {
        carPartListRepository.deleteById(carPartListId);
    }
}
