package no.kvileid.jpa.chapter9.subqueries;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    public Project() {
    }
    
    public Project(String name) {
        this.name = name;
    }
}
