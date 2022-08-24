package com.pipiolo.medicine.domain.pharmacy;

import com.pipiolo.medicine.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Pharmacy extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String phoneNumber;
    private String address;

    public Pharmacy(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
