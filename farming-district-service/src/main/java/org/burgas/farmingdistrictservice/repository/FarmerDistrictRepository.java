package org.burgas.farmingdistrictservice.repository;

import org.burgas.farmingdistrictservice.entity.FarmerDistrict;
import org.burgas.farmingdistrictservice.entity.FarmerDistrictPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerDistrictRepository extends JpaRepository<FarmerDistrict, FarmerDistrictPK> {

    void deleteByFarmerId(Long farmerId);
}
