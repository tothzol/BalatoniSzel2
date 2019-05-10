package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.client.LoginLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Component
@Transactional
public class GuestRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public GuestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<GuestEntity> guests() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GuestEntity> allGuestsQuery = cb.createQuery(GuestEntity.class);
        allGuestsQuery.from(GuestEntity.class);
        return entityManager.createQuery(allGuestsQuery).getResultList();
    }


    public GuestEntity getGuest(String guestId) {
        return entityManager.find(GuestEntity.class, guestId);
    }
    public GuestEntity getGuest(String email, String password) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GuestEntity> guestByEmailAndPasswordQuery = cb.createQuery(GuestEntity.class);
        Root<GuestEntity> root = guestByEmailAndPasswordQuery.from(GuestEntity.class);
        guestByEmailAndPasswordQuery.where(
            cb.and(
                    cb.equal(root.get("email"), email),
                    cb.equal(root.get("password"), password)
            )
        );
        return entityManager.createQuery(guestByEmailAndPasswordQuery).getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }


    public GuestEntity findOrCreateGuest(String name, String address, String document, String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GuestEntity> guestByFieldsQuery = cb.createQuery(GuestEntity.class);
        Root<GuestEntity> root = guestByFieldsQuery.from(GuestEntity.class);
        guestByFieldsQuery.where(
                cb.and(
                        cb.equal(root.get("name"), name),
                        cb.equal(root.get("address"), address),
                        cb.equal(root.get("document"), document),
                        cb.equal(root.get("email"), email)
                )
        );
        return entityManager.createQuery(guestByFieldsQuery).getResultList()
                .stream()
                .findFirst()
                .orElseGet(()->create(name, address, document, email, false));
    }



    private GuestEntity create(String name, String address, String document, String email, Boolean regular) {
        return addNewGuest(name, address, document, email, regular, email);

    }

    public GuestEntity addNewGuest(String name, String address, String document, String email, Boolean regular, String password) {

        //TODO: password generálás
        GuestEntity guest = new GuestEntity(UUID.randomUUID().toString(), name, address, document, email,regular, password, LoginLevel.GUEST);
        entityManager.persist(guest);
        return guest;
    }

    public void saveGuest(GuestEntity guest) {
        entityManager.merge(guest);
    }

}
