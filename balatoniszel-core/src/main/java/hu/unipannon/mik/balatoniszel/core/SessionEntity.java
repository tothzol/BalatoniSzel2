package hu.unipannon.mik.balatoniszel.core;

import hu.unipannon.mik.balatoniszel.server.LoginLevel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Table(name = "session")
public class SessionEntity {
    @Id
    private String id;
    @Column
    private LoginLevel level;
    @Column
    private LocalDateTime validUntil;

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
}
