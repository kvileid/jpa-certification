package no.kvileid.jpa.chapter4.onetomany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee2 {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    public static void main(String[] args) {
        PersistenceUtil.initialize(Employee2.class, Department2.class);
        PersistenceUtil.closeSession();
    }
}
