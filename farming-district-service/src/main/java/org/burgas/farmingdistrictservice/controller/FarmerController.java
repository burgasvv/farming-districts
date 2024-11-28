package org.burgas.farmingdistrictservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.farmingdistrictservice.dto.FarmerRequest;
import org.burgas.farmingdistrictservice.dto.FarmerResponse;
import org.burgas.farmingdistrictservice.service.FarmerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FarmerResponse>> getFarmersFiltered(
            @RequestParam String name, @RequestParam Long taxpayerNumber, @RequestParam Boolean archive,
            @RequestParam Long registrationDistrictId, @RequestParam LocalDate registrationDate
    ) {
        return ResponseEntity.ok(
                farmerService.findFarmersFiltered(
                        name, taxpayerNumber, registrationDistrictId, registrationDate, archive
                )
        );
    }

    @GetMapping(value = "/{farmer-id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FarmerResponse> getFarmerById(@PathVariable("farmer-id") Long farmerId) {
        return ResponseEntity.ok(farmerService.findFarmerById(farmerId));
    }

    @PostMapping(
            value = "/create",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FarmerResponse> createFarmer(@RequestBody FarmerRequest farmerRequest) {
        return ResponseEntity.ok(farmerService.create(farmerRequest));
    }

    @PutMapping(
            value = "/edit",
            produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<FarmerResponse> editFarmer(@RequestBody FarmerRequest farmerRequest) {
        return ResponseEntity.ok(farmerService.update(farmerRequest));
    }

    @PatchMapping(value = "/archive/{farmer-id}", produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> archiveFarmerById(@PathVariable("farmer-id") Long farmerId) {
        return ResponseEntity.ok(farmerService.archiveFarmerById(farmerId));
    }
}
