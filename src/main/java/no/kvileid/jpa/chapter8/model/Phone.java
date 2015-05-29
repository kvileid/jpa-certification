package no.kvileid.jpa.chapter8.model;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Embeddable
public class Phone {
    private String number;
    private String type;
    public Phone(String number, String type) {
        this.number = number;
        this.type = type;
    }
    public Phone() {
    }
    
    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
