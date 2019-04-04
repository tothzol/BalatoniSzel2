package hu.unipannon.mik.balatoniszel.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Component
@Transactional
public class SpecialDaysRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public SpecialDaysRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public boolean isSpecialDay(LocalDate day) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> isSpecialDayQuery = cb.createQuery(Long.class);
        Root<SpecialDaysEntity> root = isSpecialDayQuery.from(SpecialDaysEntity.class);
        isSpecialDayQuery
                .select(cb.count(root))
                .where(
                    cb.and(
                        cb.lessThan(root.get("startDate"), day),
                        cb.greaterThan(root.get("endDate"), day)
                    ));
        return entityManager.createQuery(isSpecialDayQuery).getSingleResult() != 0;
    }

    public void markDayAsSpecialDay(LocalDate startDate, LocalDate endDate) {
        SpecialDaysEntity newSpecialDays = new SpecialDaysEntity(UUID.randomUUID().toString(), startDate, endDate);
        entityManager.persist(newSpecialDays);
    }

    public Collection<SpecialDaysEntity> getSpecialDays() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SpecialDaysEntity> isSpecialDayQuery = cb.createQuery(SpecialDaysEntity.class);
        isSpecialDayQuery.from(SpecialDaysEntity.class);
        return entityManager.createQuery(isSpecialDayQuery).getResultList();
    }

    public void deleteSpecialDays(String id) {
        entityManager.remove(entityManager.find(SpecialDaysEntity.class, id));
    }
}
