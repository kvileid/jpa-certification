package no.kvileid.jpa.chapter12.version;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Evaluation {
    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid;
    
    public Evaluation() {
        uuid = UUID.randomUUID();
    }
    
}
