package com.learning.hibernate.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String name;
    private Date startsOn;
    @ElementCollection
    private List<Employee> resources;

    public Project(String name, Date startsOn, List<Employee> resources) {
        this.name = name;
        this.startsOn = startsOn;
        this.resources = resources;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartsOn(Date startsOn) {
        this.startsOn = startsOn;
    }

    public void setResources(List<Employee> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startsOn=" + startsOn +
                ", resources=" + resources +
                '}';
    }
}
