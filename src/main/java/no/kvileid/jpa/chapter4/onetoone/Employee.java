package no.kvileid.jpa.chapter4.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    private Department department2;
    
    public static void main(String[] args) {
        PersistenceUtil.initialize(Employee.class, Department.class);
    }
}
