package com.pipiolo.medicine.domain.pharmacy;

import com.pipiolo.medicine.web.pharmacy.PharmacySearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PharmacyRepositoryCustom {

    Slice<Pharmacy> findSliceBySearchCondition(PharmacySearchCondition searchCondition, Pageable pageable);
}
