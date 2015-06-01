package no.kvileid.jpa.chapter8.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    
    public Address(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
