package com.CarPartStoreERPBackend.carPart.boundary;

import com.CarPartStoreERPBackend.carPart.control.CarPartListService;
import com.CarPartStoreERPBackend.carPart.entity.CarPartListEntity;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/carPartList/")
public class CarPartListController {
    private final CarPartListService carPartListService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarPartListEntity> getAllParts() {
        return carPartListService.getAllCarPartLists();
    }


    @RequestMapping(method = RequestMethod.GET, path = "{carPartListId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarPartListEntity> getCarPartById(@PathVariable Long carPartListId) {
        return ResponseEntity.ok(carPartListService.getCarPartListById(carPartListId));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{carPartListId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarPartListEntity> updatePartById(@PathVariable Long carPartListId, @RequestBody CarPartListEntity newCartPartListEntity) {
        CarPartListEntity updated = carPartListService.updateCarPartListById(carPartListId, newCartPartListEntity);
        return ResponseEntity.ok(updated);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{carPartListId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getPartById(@PathVariable Long carPartListId) {
        carPartListService.deleteCarPartListById(carPartListId);
    }
}
