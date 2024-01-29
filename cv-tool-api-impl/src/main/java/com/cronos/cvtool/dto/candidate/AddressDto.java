package com.cronos.cvtool.dto.candidate;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDto {
    private String country;
    private String state;
    private String city;
    private String street;
    private String postalCode;
    private String apartment;
}