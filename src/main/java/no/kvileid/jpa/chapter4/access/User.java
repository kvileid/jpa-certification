package no.kvileid.jpa.chapter4.access;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import no.kvileid.jpa.PersistenceUtil;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    public User() {
    }
    
    public User(String name) {
        this.name = name;
    }
    
    public static void main(String[] args) {
        PersistenceUtil.initialize(User.class);
        EntityManager em = PersistenceUtil.beginTransaction();
        User u = new User("myname");
        em.persist(u);
        PersistenceUtil.commitTransaction();
        User uu = em.find(User.class, 1L);
        System.out.println(uu.name);
        PersistenceUtil.closeSession();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
