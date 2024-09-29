package com.learnsimon.rest.webservices.restful_web_service.versoning;

public class PersonV1 {
    private String name;

    public PersonV1(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                '}';
    }
}
