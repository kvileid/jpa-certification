package no.kvileid.jpa.chapter12.lifecycle;

import javax.persistence.Entity;
import javax.persistence.PrePersist;

@Entity
public class FulltimeEmployee extends Employee {
    public FulltimeEmployee() {
    }
    public FulltimeEmployee(Integer salary, String city) {
        super(salary, city);
    }
    
    @PrePersist
    public void hello2() {
        System.out.println("Fulltime");
    }
}
