package no.kvileid.jpa.havard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PersistenceUtil;

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
