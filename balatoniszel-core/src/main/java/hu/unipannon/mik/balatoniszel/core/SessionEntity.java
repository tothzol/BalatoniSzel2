package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.client.LoginLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class SessionEntity {
    @Id
    private String id;
    @Column
    private LoginLevel level;
    @Column
    private LocalDateTime validUntil;
    @Column
    private String guestId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoginLevel getLevel() {
        return level;
    }

    public void setLevel(LoginLevel level) {
        this.level = level;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
}
