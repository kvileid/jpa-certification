package no.kvileid.jpa.chapter8.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Project {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    public Project(String name) {
        this.name = name;
    }
}
