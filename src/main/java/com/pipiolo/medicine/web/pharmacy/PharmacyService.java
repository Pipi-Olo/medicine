package com.pipiolo.medicine.web.pharmacy;

import com.pipiolo.medicine.domain.pharmacy.Pharmacy;
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
        pharmacyRepository.save(new Pharmacy("별약국", "031-757-8426", "경기도 성남시 수정구 청계산로 689, 1층 103호 (고등동)"));

        return pharmacyRepository.findSliceBySearchCondition(searchCondition, pageable)
                .map(PharmacyResponse::new);
    }
}
