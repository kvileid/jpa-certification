package no.kvileid.jpa.chapter8.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    protected Department() {
    }

    public Department(String name) {
        this.name = name;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
