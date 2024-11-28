package org.burgas.farmingdistrictservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FarmerNotFoundException extends RuntimeException {

    private final String message;
}
