package no.kvileid.jpa.demo;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private final String uuid;
    
    protected BaseEntity() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof BaseEntity)) {
            return false;
        }

        return uuid.equals(((BaseEntity) other).uuid);
    }
}
