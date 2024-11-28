package org.burgas.farmingdistrictservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.farmingdistrictservice.dto.FarmerRequest;
import org.burgas.farmingdistrictservice.dto.FarmerResponse;
import org.burgas.farmingdistrictservice.entity.Farmer;
import org.burgas.farmingdistrictservice.entity.FarmerDistrict;
import org.burgas.farmingdistrictservice.exception.FarmerNotFoundException;
import org.burgas.farmingdistrictservice.mapper.FarmerMapper;
import org.burgas.farmingdistrictservice.repository.FarmerDistrictRepository;
import org.burgas.farmingdistrictservice.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, propagation = SUPPORTS)
public class FarmerService {

    private final FarmerRepository farmerRepository;
    private final FarmerDistrictRepository farmerDistrictRepository;
    private final FarmerMapper farmerMapper;

    public List<FarmerResponse> findFarmersFiltered(
            String name, Long taxpayerNumber, Long registrationDistrictId, LocalDate registrationDate, Boolean archive
    ) {
        return farmerRepository.findFarmersByNameAndTaxpayerNumberAndRegistrationDistrictIdAndRegistrationDateAndArchive(
                name, taxpayerNumber, registrationDistrictId, registrationDate, archive
        )
                .stream()
                .map(farmerMapper::toFarmerResponse)
                .toList();
    }

    public FarmerResponse findFarmerById(Long farmerId) {
        return farmerRepository.findById(farmerId)
                .map(farmerMapper::toFarmerResponse)
                .orElseGet(FarmerResponse::new);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED,
            rollbackFor = Exception.class
    )
    public FarmerResponse create(FarmerRequest farmerRequest) {
        Farmer farmer = farmerRepository.save(farmerMapper.toFarmer(farmerRequest));
        farmerRequest.getSowingFields().forEach(
                sowingFieldId -> farmerDistrictRepository.save(
                        FarmerDistrict.builder().farmerId(farmer.getId()).districtId(sowingFieldId).build()
                )
        );
        return farmerMapper.toFarmerResponse(farmer);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED,
            rollbackFor = Exception.class
    )
    public FarmerResponse update(FarmerRequest farmerRequest) {
        Farmer farmer = farmerRepository.save(farmerMapper.toFarmer(farmerRequest));
        farmerDistrictRepository.deleteByFarmerId(farmer.getId());
        farmerRequest.getSowingFields().forEach(
                sowingFieldId -> farmerDistrictRepository.save(
                        FarmerDistrict.builder().farmerId(farmer.getId()).districtId(sowingFieldId).build()
                )
        );
        return farmerMapper.toFarmerResponse(farmer);
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED,
            rollbackFor = Exception.class
    )
    public String archiveFarmerById(Long farmerId) {
        return farmerRepository.findById(farmerId)
                .map(
                        farmer -> {
                            farmer.setArchive(true);
                            farmerRepository.save(farmer);
                            return "Фермер успешно добавлен в архив";
                        }
                )
                .orElseThrow(
                        () -> new FarmerNotFoundException("Фермер с идентификатором " + farmerId + " не найден")
                );
    }
}
