package org.burgas.farmingdistrictservice;

import org.burgas.farmingdistrictservice.entity.District;
import org.burgas.farmingdistrictservice.entity.Farmer;
import org.burgas.farmingdistrictservice.entity.FarmerDistrict;
import org.burgas.farmingdistrictservice.entity.LegalForm;
import org.burgas.farmingdistrictservice.repository.DistrictRepository;
import org.burgas.farmingdistrictservice.repository.FarmerDistrictRepository;
import org.burgas.farmingdistrictservice.repository.FarmerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DistrictServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistrictServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            DistrictRepository districtRepository, FarmerRepository farmerRepository,
            FarmerDistrictRepository farmerDistrictRepository
    ) {
        return _ -> {

            List<District> districts = List.of(
                    District.builder().name("Ленинский Район").code("54-A-125").archive(false).build(),
                    District.builder().name("Калининский Район").code("54-A-130").archive(false).build(),
                    District.builder().name("Октябрьский Район").code("54-B-140").archive(false).build(),
                    District.builder().name("Центральный Район").code("54-A-150").archive(false).build(),
                    District.builder().name("Кировский Район").code("54-C-160").archive(true).build()
            );

            List<Farmer> farmers = List.of(
                    Farmer.builder().name("Веселая ферма").legalForm(LegalForm.IE).taxpayerNumber(1234567890L)
                            .additionalNumber(1234567890L).stateNumber(3458763213450L).registrationDistrictId(2L)
                            .registrationDate(LocalDate.of(2018, 10, 23)).archive(false).build(),

                    Farmer.builder().name("Супер ферма").legalForm(LegalForm.PE).taxpayerNumber(2349485732L)
                            .additionalNumber(582957386L).stateNumber(5839683028590L).registrationDistrictId(3L)
                            .registrationDate(LocalDate.of(2015, 5, 8)).archive(false).build()
            );

            List<FarmerDistrict> farmerDistricts = List.of(
                    FarmerDistrict.builder().farmerId(1L).districtId(1L).build(),
                    FarmerDistrict.builder().farmerId(1L).districtId(2L).build(),
                    FarmerDistrict.builder().farmerId(1L).districtId(3L).build(),
                    FarmerDistrict.builder().farmerId(2L).districtId(4L).build(),
                    FarmerDistrict.builder().farmerId(2L).districtId(5L).build()
            );

            districtRepository.saveAll(districts);
            farmerRepository.saveAll(farmers);
            farmerDistrictRepository.saveAll(farmerDistricts);
        };
    }
}
