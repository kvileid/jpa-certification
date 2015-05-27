package no.kvileid.jpa.chapter4.onetomany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String depName;
    
}
