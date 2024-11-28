package org.burgas.farmingdistrictservice.repository;

import org.burgas.farmingdistrictservice.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    List<Farmer> findFarmersByNameAndTaxpayerNumberAndRegistrationDistrictIdAndRegistrationDateAndArchive(
            String name, Long taxpayerNumber, Long registrationDistrictId, LocalDate registrationDate, Boolean archive
    );
}
