package no.kvileid.jpa.chapter4.attributeoverride;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private int number;
}
