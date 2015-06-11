package no.kvileid.jpa.chapter10.associationoverride;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;

@Embeddable
public class ContactInfo {
    @Embedded
    Address residence;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRI_NUM")
    Phone primaryPhone;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @MapKey(name = "type")
    @JoinTable(name="EMP_PHONES")
    Map<String, Phone> phones;
    
}
