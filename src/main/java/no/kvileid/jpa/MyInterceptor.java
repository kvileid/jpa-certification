package no.kvileid.jpa;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

@SuppressWarnings("serial")
public class MyInterceptor extends EmptyInterceptor {
    private final Objenesis objenesis;
    
    public MyInterceptor() {
        objenesis = new ObjenesisStd(true); 
    }
    
    @Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
        return allocateInstance(getClass(entityName));
    }

    private Class<?> getClass(String entityName) {
        try {
            return Class.forName(entityName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T allocateInstance(Class<T> clazz) {
        return objenesis.getInstantiatorOf(clazz).newInstance();
    }
}
