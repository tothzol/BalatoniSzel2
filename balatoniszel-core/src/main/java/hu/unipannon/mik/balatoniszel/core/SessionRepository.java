package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.server.LoginLevel;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Component
@Transactional
public class SessionRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public SessionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SessionEntity createSession(LoginLevel loginLevel, LocalDateTime now) {
        SessionEntity newSession = new SessionEntity();
        newSession.setId(UUID.randomUUID().toString());
        newSession.setLevel(loginLevel);
        newSession.setValidUntil(now.plus(10, ChronoUnit.MINUTES));
        entityManager.persist(newSession);
        return newSession;
    }

    public SessionEntity getSession(String id) {
        return entityManager.find(SessionEntity.class, id);
    }

    public List<SessionEntity> expiredSessions(LocalDateTime now) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SessionEntity> expiredSessionsQuery = cb.createQuery(SessionEntity.class);
        Root<ReservationEntity> root = notExpiredReservationsQuery.from(ReservationEntity.class);
        notExpiredReservationsQuery.where(cb.isFalse(root.get("expired")));
        return entityManager.createQuery(notExpiredReservationsQuery).getResultList();
    }
}
