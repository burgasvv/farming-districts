package org.burgas.farmingdistrictservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.burgas.farmingdistrictservice.entity.LegalForm;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmerRequest {

    private Long id;
    private String name;
    private LegalForm legalForm;
    private Long taxpayerNumber;
    private Long additionalNumber;
    private Long stateNumber;
    private Long registrationDistrictId;
    private List<Long> sowingFields;
    private LocalDate registrationDate;
    private Boolean archive;
}
