package no.kvileid.jpa.chapter4.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String depName;
    @OneToOne(mappedBy="department2")
    private Employee employee;
    
}
