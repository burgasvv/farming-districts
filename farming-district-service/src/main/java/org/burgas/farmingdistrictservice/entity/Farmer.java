package org.burgas.farmingdistrictservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farmer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private LegalForm legalForm;

    @Column(nullable = false)
    private Long taxpayerNumber; // ИНН 10
    private Long additionalNumber; // КПП 9
    private Long stateNumber; //ОГРН 13
    private Long registrationDistrictId;
    private LocalDate registrationDate;
    private Boolean archive;
}
