package com.pipiolo.medicine.domain.pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository
        extends JpaRepository<Pharmacy, Long>, PharmacyRepositoryCustom
{
}
