package com.learnsimon.rest.webservices.restful_web_service.versoning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersoningPersonController {
    @GetMapping("/v1/person")
    public PersonV1 getFirstPersonOfPerson(){
        return new PersonV1("Bob Charli");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Suman", "Astani"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter(){
        return new PersonV1("Bob Charli");
    }
    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter(){
        return new PersonV2(new Name("Simon", "Astani"));
    }
}
