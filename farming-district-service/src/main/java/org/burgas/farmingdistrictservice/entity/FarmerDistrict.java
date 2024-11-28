package org.burgas.farmingdistrictservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@IdClass(FarmerDistrictPK.class)
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDistrict {

    @Id
    private Long farmerId;

    @Id
    private Long districtId;
}
