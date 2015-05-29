package no.kvileid.jpa.chapter8.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long salary;
    @ElementCollection
    private Map<String, Phone> phones;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
    @OneToOne(cascade = CascadeType.ALL)
    private Project project;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Employee() {
    }

    public Employee(String name, Long salary, Department department, Project project, Address a) {
        this.name = name;
        this.salary = salary;
        this.phones = new HashMap<>();
        this.department = department;
        this.project = project;
        this.address = a;
    }

    public void addPhone(Phone phone) {
        phones.put(phone.getType(), phone);
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
