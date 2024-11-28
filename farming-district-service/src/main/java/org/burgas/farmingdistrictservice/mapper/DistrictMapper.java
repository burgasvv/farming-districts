package org.burgas.farmingdistrictservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.farmingdistrictservice.dto.DistrictRequest;
import org.burgas.farmingdistrictservice.dto.DistrictResponse;
import org.burgas.farmingdistrictservice.entity.District;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DistrictMapper {

    public District toDistrict(DistrictRequest districtRequest) {
        return District.builder()
                .id(districtRequest.getId())
                .name(districtRequest.getName())
                .code(districtRequest.getCode())
                .archive(false)
                .build();
    }

    public DistrictResponse toDistrictResponse(District district) {
        return DistrictResponse.builder()
                .id(district.getId())
                .name(district.getName())
                .code(district.getCode())
                .archive(district.getArchive())
                .build();
    }
}
