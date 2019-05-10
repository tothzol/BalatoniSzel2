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

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class ReservationRepository {

    @PersistenceContext
    private final EntityManager entityManager;


    @Autowired
    public ReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addReservation(ReservationEntity reservation) {
        entityManager.persist(reservation);
    }

    public List<ReservationEntity> reservations() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReservationEntity> allReservationsQuery = cb.createQuery(ReservationEntity.class);
        allReservationsQuery.from(ReservationEntity.class);
        return entityManager.createQuery(allReservationsQuery).getResultList();
    }

    public List<ReservationEntity> notExpiredReservations() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReservationEntity> notExpiredReservationsQuery = cb.createQuery(ReservationEntity.class);
        Root<ReservationEntity> root = notExpiredReservationsQuery.from(ReservationEntity.class);
        notExpiredReservationsQuery.where(cb.isFalse(root.get("expired")));
        return entityManager.createQuery(notExpiredReservationsQuery).getResultList();
    }

    public List<RoomEntity> reservedRooms(LocalDate date, RoomRepository roomRepository) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReservationEntity> reservationsOnDayQuery = cb.createQuery(ReservationEntity.class);
        Root<ReservationEntity> root = reservationsOnDayQuery.from(ReservationEntity.class);
        reservationsOnDayQuery.where(
                cb.or(
                    cb.lessThan(root.get("arrivalDate"), date),
                    cb.greaterThan(root.get("departureDate"), date)
                 )
        );

        return entityManager.createQuery(reservationsOnDayQuery).getResultList()
                .stream()
                .map(ReservationEntity::getRoomId)
                .map(roomRepository::getRoom)
                .collect(Collectors.toList());
    }

    public ReservationEntity getReservation(String reservationId) {
        return entityManager.find(ReservationEntity.class, reservationId);
    }

    public void saveReservation(ReservationEntity reservation) {
        entityManager.merge(reservation);

    }

    public List<ReservationEntity> reservationsFor(GuestEntity guest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReservationEntity> guestReservationsQuery = cb.createQuery(ReservationEntity.class);
        Root<ReservationEntity> root = guestReservationsQuery.from(ReservationEntity.class);
        guestReservationsQuery.where(cb.equal(root.get("guestId"), guest.getId()));
        return entityManager.createQuery(guestReservationsQuery).getResultList();
    }
}
