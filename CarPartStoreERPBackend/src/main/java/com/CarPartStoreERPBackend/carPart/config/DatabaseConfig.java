package com.CarPartStoreERPBackend.carPart.config;

import com.CarPartStoreERPBackend.carPart.control.CarPartListRepository;
import com.CarPartStoreERPBackend.carPart.control.CarPartRepository;
import com.CarPartStoreERPBackend.carPart.entity.CarMarke;
import com.CarPartStoreERPBackend.carPart.entity.CarPartEntity;
import com.CarPartStoreERPBackend.carPart.entity.CarPartListEntity;
import com.CarPartStoreERPBackend.carPart.entity.CarPartType;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@Data
@Configuration
@EnableJpaAuditing
public class DatabaseConfig {
    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);
    private List<String> allowedOrigins = List.of("http://localhost:4200");
    private List<String> allowedMethods = List.of("GET", "POST", "PUT", "DELETE", "PATCH");

    @Bean
    CommandLineRunner initDatabaseCarPart(CarPartRepository repository, CarPartListRepository carPartListRepository) {
        CarPartListEntity allSingleParts = carPartListRepository.save(CarPartListEntity.builder()
                .name("Alle einzelnen").barCode(generateBarCode()).totalWeightInKg(8.0).volumeInCube(20.0).build());
        CarPartListEntity trunkList = carPartListRepository.save(CarPartListEntity.builder()
                .name("KofferraumKlapper").barCode(generateBarCode()).totalWeightInKg(8.0).volumeInCube(20.0).build());
        CarPartListEntity bumperList = carPartListRepository.save(CarPartListEntity.builder()
                .name("Stoßstange").barCode(generateBarCode()).totalWeightInKg(8.0).volumeInCube(20.0).build());
        CarPartListEntity doubleSideMirrorList = carPartListRepository.save(CarPartListEntity.builder()
                .name("Seitenspiegel").barCode(generateBarCode()).totalWeightInKg(2.0).volumeInCube(1.0).build());
        CarPartListEntity setOfLackList = carPartListRepository.save(CarPartListEntity.builder()
                .name("Lack").barCode(generateBarCode()).totalWeightInKg(0.6).volumeInCube(0.2).build());
        CarPartListEntity setOfDoorsList = carPartListRepository.save(CarPartListEntity.builder()
                .name("Türen").barCode(generateBarCode()).totalWeightInKg(0.6).volumeInCube(0.2).build());
        List<CarPartEntity> allSingListEntities = new java.util.ArrayList<>(List.of(
                generateSingleCarPart(allSingleParts, createCarPart("Windschutzscheibe", 499.99, CarMarke.AUDI, CarPartType.SCHEIBEN, "A4", 2,3,4,2.0, 8.0)),
                generateSingleCarPart(trunkList, createCarPart("KofferraumKlapper", 349.99, CarMarke.AUDI, CarPartType.KAROSSERIE, "A6",1,1,1, 0.45, 5.25)),
                generateSingleCarPart(allSingleParts, createCarPart("Innenspiegel", 79.99, CarMarke.SONSTIGES, CarPartType.INNEN_AUSSTATTUNG, "Sonstiges",2,3,4, 0.001, 0.56)),
                generateSingleCarPart(bumperList, createCarPart("Stoßstange", 349.99, CarMarke.MERCEDES_BENZ, CarPartType.KAROSSERIE, "E-230 Classic", 1,1,1, 0.8, 12.0)),
                generateSingleCarPart(allSingleParts, createCarPart("Motoröl", 79.99, CarMarke.SONSTIGES, CarPartType.FLUESSIGKEIT, "Sonstiges", 1,5,4,0.018, 1.2)),
                generateSingleCarPart(allSingleParts, createCarPart("Frostschutz", 29.99, CarMarke.SONSTIGES, CarPartType.FLUESSIGKEIT, "Sonstiges", 3,5,4,0.02, 2.5)),
                generateSingleCarPart(allSingleParts, createCarPart("Radio", 149.99, CarMarke.SONSTIGES, CarPartType.INNEN_AUSSTATTUNG, "Sonstiges", 1,1,1, 0.12, 0.3)),
                generateSingleCarPart(allSingleParts, createCarPart("4 x Sitzbezug", 49.99, CarMarke.SONSTIGES, CarPartType.INNEN_AUSSTATTUNG, "Sonstiges", 2,4,6,0.1, 1.5)),
                generateSingleCarPart(allSingleParts, createCarPart("4xStahlfelgen", 399.99, CarMarke.NISSAN, CarPartType.KAROSSERIE, "Qashqai", 2,5,8,1.4, 24.0)),
                generateSingleCarPart(allSingleParts, createCarPart("4 x Kennzeichenhalter", 19.99, CarMarke.SONSTIGES, CarPartType.KAROSSERIE, "Sonstiges", 3,2,8,0.001, 0.4))));

        List<CarPartEntity> doubleEntities = generateDoubleCarParts(doubleSideMirrorList, List.of(
                createCarPart("Seitenspiegel  (Fahrer)", 39.99, CarMarke.VOLKSWAGEN, CarPartType.SCHEIBEN, "Passat", 2,3, 2, 0.002, 0.8),
                createCarPart("Seitenspiegel  (Beifahrer)", 39.99, CarMarke.VOLKSWAGEN, CarPartType.SCHEIBEN, "Passat", 2,2,3,0.002, 0.8)
        ));

        List<CarPartEntity> setOfLack = generateSetOfCarParts(setOfLackList, List.of(
                createCarPart("Lack(Schwarz)", 9.99, CarMarke.SONSTIGES, CarPartType.FLUESSIGKEIT, "Sonstiges", 3, 4, 8,0.001, 0.02),
                createCarPart("Lack(Weiss)", 9.99, CarMarke.SONSTIGES, CarPartType.FLUESSIGKEIT, "Sonstiges", 3,4,8, 0.001, 0.02),
                createCarPart("Lack(Blau)", 9.99, CarMarke.SONSTIGES, CarPartType.FLUESSIGKEIT, "Sonstiges",3,4, 8,0.001, 0.02),
                createCarPart("Lack(Grau)", 9.99, CarMarke.SONSTIGES, CarPartType.FLUESSIGKEIT, "Sonstiges", 1,4,8,0.001, 0.02)
        ));
        List<CarPartEntity> setOfDoors = generateSetOfCarParts(setOfDoorsList, List.of(
                createCarPart("Tür vorn Links(Beifahrer)", 199.99, CarMarke.MAZDA, CarPartType.KAROSSERIE, "6", 1,1, 1,0.8, 12.0),
                createCarPart("Tür vorn Rechts(Fahrer)", 199.99, CarMarke.MAZDA, CarPartType.KAROSSERIE, "6",1,1,1, 0.8, 12.0),
                createCarPart("Tür hinten Links(Beifahrer)", 199.99, CarMarke.MAZDA, CarPartType.KAROSSERIE, "6",1,1, 1,0.8, 12.0),
                createCarPart("Tür hinten Rechts(Fahrer) ", 199.99, CarMarke.MAZDA, CarPartType.KAROSSERIE, "6",1,1,1, 0.8, 12.0),
                createCarPart("Hintertür", 229.99, CarMarke.MAZDA, CarPartType.KAROSSERIE, "6", 1,1, 1,0.75, 10.0)));
        allSingListEntities.addAll(doubleEntities);
        allSingListEntities.addAll(setOfLack);
        allSingListEntities.addAll(setOfDoors);

        return args -> {
            for (CarPartEntity carPartEntity : allSingListEntities) {
                log.info("Preloading: " + repository.save(carPartEntity));
            }
        };
    }

    private CarPartEntity generateSingleCarPart(CarPartListEntity savedList, CarPartEntity carPart) {
        savedList.setCarParts(List.of(carPart));
        carPart.setCarPartList(savedList);
        return carPart;
    }

    private List<CarPartEntity> generateDoubleCarParts(CarPartListEntity savedList, List<CarPartEntity> carParts) {
        savedList.setCarParts(carParts);
        return savedList.getCarParts().stream().peek(carPart -> carPart.setCarPartList(savedList)).toList();
    }

    private List<CarPartEntity> generateSetOfCarParts(CarPartListEntity savedList, List<CarPartEntity> carParts) {
        savedList.setCarParts(carParts);
        return savedList.getCarParts().stream().peek(carPart -> carPart.setCarPartList(savedList)).toList();
    }

    private CarPartEntity createCarPart(String name, Double price, CarMarke marke,
                                        CarPartType partType, String model,
                                        Integer quantity, Integer packageCount, Integer quantityLimit,
                                        Double volumeInCube, Double weightInKg) {
        return CarPartEntity.builder()
                .name(name)
                .totalWeightInKg(weightInKg)
                .quantity(quantity)
                .packageCount(packageCount)
                .quantityLimit(quantityLimit)
                .carMarke(marke)
                .carModel(model)
                .carPartType(partType)
                .volumeInCube(volumeInCube)
                .price(price)
                .rabatt(1.0)
                .barCode(generateBarCode())
                .build();
    }

    private String generateBarCode() {
        return java.util.UUID.randomUUID().toString();
    }
}
