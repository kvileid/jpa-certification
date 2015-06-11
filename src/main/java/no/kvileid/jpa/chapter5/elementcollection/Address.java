package no.kvileid.jpa.chapter5.elementcollection;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    
    public Address() {
        // TODO Auto-generated constructor stub
    }

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
}