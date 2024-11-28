package org.burgas.farmingdistrictservice.mapper;

import lombok.RequiredArgsConstructor;
import org.burgas.farmingdistrictservice.dto.FarmerRequest;
import org.burgas.farmingdistrictservice.dto.FarmerResponse;
import org.burgas.farmingdistrictservice.entity.District;
import org.burgas.farmingdistrictservice.entity.Farmer;
import org.burgas.farmingdistrictservice.repository.DistrictRepository;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class FarmerMapper {

    private final DistrictRepository districtRepository;

    public Farmer toFarmer(FarmerRequest farmerRequest) {
        return Farmer.builder()
                .id(farmerRequest.getId())
                .name(farmerRequest.getName())
                .legalForm(farmerRequest.getLegalForm())
                .taxpayerNumber(farmerRequest.getTaxpayerNumber())
                .additionalNumber(farmerRequest.getAdditionalNumber())
                .stateNumber(farmerRequest.getStateNumber())
                .registrationDistrictId(farmerRequest.getRegistrationDistrictId())
                .registrationDate(farmerRequest.getRegistrationDate())
                .archive(false)
                .build();
    }

    public FarmerResponse toFarmerResponse(Farmer farmer) {
        return FarmerResponse.builder()
                .id(farmer.getId())
                .name(farmer.getName())
                .legalForm(farmer.getLegalForm())
                .taxpayerNumber(farmer.getTaxpayerNumber())
                .additionalNumber(farmer.getAdditionalNumber())
                .stateNumber(farmer.getStateNumber())
                .registrationDistrict(
                        districtRepository.findById(farmer.getRegistrationDistrictId())
                                .map(District::getName)
                                .orElse("")
                )
                .sowingFields(
                        districtRepository.findSowingFieldsForFarmer(farmer.getId())
                                .stream().map(District::getName).toList()
                )
                .registrationDate(
                        farmer.getRegistrationDate().format(
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")
                        )
                )
                .archive(farmer.getArchive())
                .build();
    }
}
