package com.learnsimon.rest.webservices.restful_web_service.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    private UserDoaService service;
    //constructor injection
    public UserResource(UserDoaService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User>  retriveAllUser(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User  retriveUser(@PathVariable Integer id){
        User user =  service.findUser(id);
        if(user == null)
            throw new UserNotFoundExcepiton("id: " + id);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteById(id);
    }


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User savedUser = service.save(user);
        //location -header  /users/4 => uri
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();


    }




}
