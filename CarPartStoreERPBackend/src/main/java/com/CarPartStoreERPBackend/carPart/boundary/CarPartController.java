package com.CarPartStoreERPBackend.carPart.boundary;

import com.CarPartStoreERPBackend.carPart.control.CarPartService;
import com.CarPartStoreERPBackend.carPart.entity.CarPartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(path = "api/carPart/")
public class CarPartController {

    private final CarPartService carPartService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarPartEntity> getAllParts() {
        return carPartService.getAllCarParts();
    }


    @RequestMapping(method = RequestMethod.GET, path = "{carPartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarPartEntity> getCarPartById(@PathVariable Long carPartId) {
        return ResponseEntity.ok(carPartService.getCarPartById(carPartId));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarPartEntity> addPart(@RequestBody CarPartEntity newCartPartEntity) {
        CarPartEntity added = carPartService.addCarPart(newCartPartEntity);
        return ResponseEntity.ok(added);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{carPartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarPartEntity> updatePartById(@PathVariable Long carPartId, @RequestBody CarPartEntity newCartPartEntity) {
        CarPartEntity updated = carPartService.updateCarPartById(carPartId, newCartPartEntity);
        return ResponseEntity.ok(updated);
    }

    @RequestMapping(method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarPartEntity> orderParts(@RequestBody List<CarPartEntity> partsToOrder) {
        return carPartService.orderParts(partsToOrder);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{carPartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getPartById(@PathVariable Long carPartId) {
        carPartService.deleteCarPartById(carPartId);
    }
}
