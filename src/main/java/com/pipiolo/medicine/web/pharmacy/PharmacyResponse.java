package com.pipiolo.medicine.web.pharmacy;

import com.pipiolo.medicine.domain.pharmacy.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PharmacyResponse {

    private String name;
    private String phoneNumber;
    private String address;

    public PharmacyResponse(Pharmacy pharmacy) {
        this.name = pharmacy.getName();
        this.phoneNumber = pharmacy.getPhoneNumber();
        this.address = pharmacy.getAddress();
    }
}
