package com.interviewMe.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    //basic versioning
    @GetMapping("/v1/person")
    public PersonV1 retrievePersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 retrievePersonV2() {
        return new PersonV2(new Name("Bob", "Annie", " Charlie"));
    }

    //parameter versioning
    @GetMapping(value="/person/param",params = "version=1")
    public PersonV1 retrieveParamV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="/person/param",params = "version=2")
    public PersonV2 retrieveParamV2() {
        return new PersonV2(new Name("Bob", "Annie", " Charlie"));
    }

    //header versioning
    @GetMapping(value="/person/header",headers = "X-API-VERSION=1")
    public PersonV1 retrieveHeadersV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="/person/header",headers = "X-API-VERSION=2")
    public PersonV2 retrieveHeadersV2() {
        return new PersonV2(new Name("Bob", "Annie", " Charlie"));
    }

    //MIME versioning - most commonly used
    @GetMapping(value="/person/produces",produces = "application/com.company.app-v1+json")
    public PersonV1 retrieveProducesV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(value="/person/produces",produces = "application/com.company.app-v2+json")
    public PersonV2 retrieveProducesV2() {
        return new PersonV2(new Name("Bob", "Annie", " Charlie"));
    }

}
