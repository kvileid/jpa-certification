package no.kvileid.jpa.chapter10.associationoverride;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    String street;
    String city;
}
