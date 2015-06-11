package no.kvileid.jpa.chapter5.onetomanymap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String city;
    @ManyToOne
    Employee emp;
    
    public Address() {
        // TODO Auto-generated constructor stub
    }

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
}