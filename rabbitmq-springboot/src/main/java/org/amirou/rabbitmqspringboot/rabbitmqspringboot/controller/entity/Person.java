package org.amirou.rabbitmqspringboot.rabbitmqspringboot.controller.entity;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 2813363400628633158L;
    long id;
    String name;

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
