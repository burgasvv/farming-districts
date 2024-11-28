package org.burgas.farmingdistrictservice.controller;

import lombok.RequiredArgsConstructor;
import org.burgas.farmingdistrictservice.dto.DistrictRequest;
import org.burgas.farmingdistrictservice.dto.DistrictResponse;
import org.burgas.farmingdistrictservice.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/districts")
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DistrictResponse>> getNotArchivedDistrictsFilteredBy(@RequestParam String filter) {
        return ResponseEntity.ok(districtService.findNotArchivedDistrictsFilteredBy(filter));
    }

    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<DistrictResponse> createDistrict(@RequestBody DistrictRequest districtRequest) {
        return ResponseEntity.ok(districtService.createOrUpdate(districtRequest));
    }

    @PutMapping(value = "/edit", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<DistrictResponse> editDistrict(@RequestBody DistrictRequest districtRequest) {
        return ResponseEntity.ok(districtService.createOrUpdate(districtRequest));
    }

    @PatchMapping("/archive/{district-id}")
    public ResponseEntity<String> archiveDistrict(@PathVariable("district-id") Long districtId) {
        return ResponseEntity.ok(districtService.archive(districtId));
    }
}
