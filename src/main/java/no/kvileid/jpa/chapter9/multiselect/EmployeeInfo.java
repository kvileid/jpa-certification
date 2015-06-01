package no.kvileid.jpa.chapter9.multiselect;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EmployeeInfo {
    private String name;
    private Integer salary;

    public EmployeeInfo(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
