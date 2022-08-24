package com.pipiolo.medicine.domain.pharmacy;

import com.pipiolo.medicine.web.pharmacy.PharmacySearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static com.pipiolo.medicine.domain.pharmacy.QPharmacy.*;

public class PharmacyRepositoryCustomImpl implements PharmacyRepositoryCustom {

    private final JPAQueryFactory query;

    public PharmacyRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Slice<Pharmacy> findSliceBySearchCondition(
            PharmacySearchCondition searchCondition,
            Pageable pageable
    ) {
        String pharmacyName = searchCondition.getPharmacyName();
        String address = searchCondition.getAddress();

        List<Pharmacy> content = query
                .select(pharmacy)
                .from(pharmacy)
                .where(nameContains(pharmacyName), addressContains(address))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

    private BooleanExpression nameContains(String pharmacyName) {
        if (StringUtils.hasText(pharmacyName)) {
            return pharmacy.name.contains(pharmacyName);
        }
        return null;
    }

    private BooleanExpression addressContains(String address) {
        if (StringUtils.hasText(address)) {
            pharmacy.address.contains(address);
        }
        return null;
    }
}

