package no.kvileid.jpa.chapter4.attributeoverride;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @AttributeOverrides({
        // If multiple AttributeOverride must wrap in AttributeOverrides 
        @AttributeOverride(name = "street", column = @Column(name = "STREEET")),
        @AttributeOverride(name = "number", column = @Column(name = "NUUUMBER"))
    })
    private Address address;

    public static void main(String[] args) {
        PersistenceUtil.initialize(Employee.class);
    }
}
