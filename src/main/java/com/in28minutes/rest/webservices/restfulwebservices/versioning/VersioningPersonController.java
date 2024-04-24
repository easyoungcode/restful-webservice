package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getVersionOfPersonRequestParam() {
        return new PersonV1("Bob charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParam() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    /** Header에 매개변수(X-API~)가 있어야 함, 해당 매개변수 값으로 구분 */
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader() {
        return new PersonV1("Bob charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    /** Accept Header가 있어야 함. produces 값을 Accept의 값으로 넣음 */
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Bob charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
