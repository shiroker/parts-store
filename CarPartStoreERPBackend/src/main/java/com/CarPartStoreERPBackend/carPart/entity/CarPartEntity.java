package com.CarPartStoreERPBackend.carPart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@Getter
@Setter
@Table(name = "car_part")
public class CarPartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String barCode;
    @Column
    private Double price;
    @Column
    private Double rabatt;
    @Column
    private CarMarke carMarke;
    @Column
    private CarPartType carPartType;
    @Column
    private String carModel;
    @Column
    private Double volumeInCube;
    @Column
    private Double totalWeightInKg;
    @Column
    private Integer quantity;
    @Column
    private Integer packageCount;
    @Column
    private Integer quantityLimit;
    @ManyToOne
    @JoinColumn(name = "car_part_list")
    @JsonBackReference(value = "car_part_list_id")
    private CarPartListEntity carPartList;


}
