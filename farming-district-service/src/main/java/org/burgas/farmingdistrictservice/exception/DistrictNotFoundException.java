package org.burgas.farmingdistrictservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DistrictNotFoundException extends RuntimeException{

    private final String message;
}
