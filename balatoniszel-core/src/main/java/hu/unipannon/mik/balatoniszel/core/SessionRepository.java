package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.client.LoginLevel;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
@Transactional
public class SessionRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public SessionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SessionEntity createSession(GuestEntity guest, LoginLevel loginLevel, LocalDateTime now) {
        SessionEntity newSession = new SessionEntity();
        newSession.setId(UUID.randomUUID().toString());
        newSession.setLevel(loginLevel);
        newSession.setValidUntil(now.plus(10, ChronoUnit.MINUTES));
        newSession.setGuestId(guest.getId());
        entityManager.persist(newSession);
        return newSession;
    }

    public SessionEntity getSession(String id) {
        return entityManager.find(SessionEntity.class, id);
    }

    public void deleteExpiredSessions(LocalDateTime now) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<SessionEntity> delete = cb.createCriteriaDelete(SessionEntity.class);
        Root<SessionEntity> root = delete.from(SessionEntity.class);
        delete.where(cb.lessThanOrEqualTo(root.get("validUntil"), now));
        entityManager.createQuery(delete).executeUpdate();
    }

    public void logout(String token) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<SessionEntity> delete = cb.createCriteriaDelete(SessionEntity.class);
        Root<SessionEntity> root = delete.from(SessionEntity.class);
        delete.where(cb.equal(root.get("id"), token));
        entityManager.createQuery(delete).executeUpdate();
    }
}
