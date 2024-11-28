package org.burgas.farmingdistrictservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictRequest {

    private Long id;
    private String name;
    private String code;
    private Boolean archive;
}
