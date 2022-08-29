package com.pipiolo.medicine.web.pharmacy;

import com.pipiolo.medicine.domain.pharmacy.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public Slice<PharmacyResponse> findAll(
            PharmacySearchCondition searchCondition,
            Pageable pageable
    ) {
        return pharmacyRepository.findSliceBySearchCondition(searchCondition, pageable)
                .map(PharmacyResponse::new);
    }
}
