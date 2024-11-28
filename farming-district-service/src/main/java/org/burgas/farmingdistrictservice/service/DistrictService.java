package org.burgas.farmingdistrictservice.service;

import lombok.RequiredArgsConstructor;
import org.burgas.farmingdistrictservice.dto.DistrictRequest;
import org.burgas.farmingdistrictservice.dto.DistrictResponse;
import org.burgas.farmingdistrictservice.exception.DistrictNotFoundException;
import org.burgas.farmingdistrictservice.mapper.DistrictMapper;
import org.burgas.farmingdistrictservice.repository.DistrictRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, propagation = SUPPORTS)
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public List<DistrictResponse> findNotArchivedDistrictsFilteredBy(String filter) {
        return districtRepository.findDistrictsByFilter(filter)
                .stream()
                .map(districtMapper::toDistrictResponse)
                .toList();
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED,
            rollbackFor = Exception.class
    )
    public DistrictResponse createOrUpdate(DistrictRequest districtRequest) {
        return districtMapper.toDistrictResponse(
                districtRepository.save(districtMapper.toDistrict(districtRequest))
        );
    }

    @Transactional(
            isolation = SERIALIZABLE,
            propagation = REQUIRED,
            rollbackFor = Exception.class
    )
    public String archive(Long districtId) {
        return districtRepository.findById(districtId)
                .map(
                        district -> {
                            district.setArchive(true);
                            districtRepository.save(district);
                            return "Район успешно отправлен в архив";
                        }
                )
                .orElseThrow(
                        () -> new DistrictNotFoundException(
                                "Архив с идентификатором " + districtId + " не найден"
                        )
                );
    }
}
