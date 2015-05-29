package no.kvileid.jpa.chapter7.bulk;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private final String depName;

    protected Department() {
        this.depName = null;
    }

    public Department(String depName) {
        this.depName = depName;
    }
    
    public String toString() {
        return depName;
    }
}
