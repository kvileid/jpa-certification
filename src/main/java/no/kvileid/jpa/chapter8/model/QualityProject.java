package no.kvileid.jpa.chapter8.model;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class QualityProject extends Project {

    public QualityProject(String name) {
        super(name);
    }
    public QualityProject() {
        super("");
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
