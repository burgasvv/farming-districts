package org.burgas.farmingdistrictservice.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDistrictPK {

    private Long farmerId;
    private Long districtId;
}
