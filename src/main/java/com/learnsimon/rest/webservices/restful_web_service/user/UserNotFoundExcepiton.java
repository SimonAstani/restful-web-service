package com.learnsimon.rest.webservices.restful_web_service.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundExcepiton extends RuntimeException{
    public UserNotFoundExcepiton(String s) {
        super(s);
    }
}
