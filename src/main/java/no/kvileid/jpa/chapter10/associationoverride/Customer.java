package no.kvileid.jpa.chapter10.associationoverride;

import java.util.HashMap;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    Long id;
    @Embedded
    @AttributeOverride(name="residence.street", column = @Column(name = "SSTREET"))
    @AssociationOverrides( {
            @AssociationOverride(name = "primaryPhone", joinColumns = @JoinColumn(name="CUST_PHONE")),
            @AssociationOverride(name="phones", joinTable = @JoinTable(name="CUST_PHONES"))
    })
    ContactInfo contactInfo;

    public static void main(String[] args) {
        try {
            PersistenceUtil.initialize(Customer.class, ContactInfo.class, Phone.class, Address.class);

            Address a = new Address();
            a.city = "Oslo";
            a.street = "Neptunveien";
            Phone p = new Phone();
            p.number = "12345678";
            p.type = "home";
            ContactInfo ci = new ContactInfo();
            ci.residence = a;
            ci.primaryPhone = p;
            ci.phones = new HashMap<>();
            ci.phones.put(p.type, p);
            Customer c = new Customer();
            c.contactInfo = ci;

            EntityManager em = PersistenceUtil.beginTransaction();
            em.persist(p);
            em.persist(c);
            PersistenceUtil.commitTransaction();
            PersistenceUtil.clear();
            
            Customer customer = em.find(Customer.class, 1L);
        } finally {
            PersistenceUtil.closeSession();
        }
    }
}
