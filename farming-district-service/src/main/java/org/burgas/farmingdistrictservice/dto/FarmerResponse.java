package org.burgas.farmingdistrictservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.burgas.farmingdistrictservice.entity.LegalForm;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmerResponse {

    private Long id;
    private String name;
    private LegalForm legalForm;
    private Long taxpayerNumber;
    private Long additionalNumber;
    private Long stateNumber;
    private String registrationDistrict;
    private List<String> sowingFields;
    private String registrationDate;
    private Boolean archive;
}
