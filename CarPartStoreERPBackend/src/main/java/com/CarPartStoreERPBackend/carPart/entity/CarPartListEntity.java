package com.CarPartStoreERPBackend.carPart.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car_part_list")
public class CarPartListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String name;
    @Column
    String barCode;
    @Column
    Double volumeInCube;
    @Column
    Double totalWeightInKg;
    @OneToMany(mappedBy = "carPartList", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<CarPartEntity> carParts = new ArrayList<>();

    @Override
    public String toString() {
        return name + barCode;
    }
    private Double getTotalVolumeInCube() {
        return this.carParts.stream().mapToDouble(CarPartEntity::getVolumeInCube).sum();
    }
    private Double getTotalWeight() {
        return this.carParts.stream().mapToDouble(CarPartEntity::getTotalWeightInKg).sum();
    }

}
