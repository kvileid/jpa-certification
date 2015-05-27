package no.kvileid.jpa.chapter4.onetomany;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Department2 {
    @Id
    @GeneratedValue
    private Long id;
    private String depName;
    @OneToMany
    @JoinColumn(name="dept")
    private List<Employee2> employee;
    
}
