package no.kvileid.jpa.chapter5.orderedlist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String depName;
    @ManyToOne
    private Employee employee;
    
}
