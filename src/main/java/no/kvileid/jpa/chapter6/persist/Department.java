package no.kvileid.jpa.chapter6.persist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String depName;
    @ManyToOne
    private Employee employee;
    
}
