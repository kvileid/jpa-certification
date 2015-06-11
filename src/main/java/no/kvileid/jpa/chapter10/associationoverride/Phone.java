package no.kvileid.jpa.chapter10.associationoverride;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Phone {
    @Id
    @GeneratedValue
    Long id;
    String type;
    String number;
}
