package com.learnsimon.rest.webservices.restful_web_service.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {
    private UserDoaService service;
    //constructor injection
    public UserResource(UserDoaService service){
        this.service = service;
    }

    @GetMapping("/user")
    public List<User>  retriveAllUser(){
        return service.findAll();
    }


    //made certain chenages
    // localhost:8080/users
    // RepresenationMoel or EntityModel
    //  WebMvcLinkBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User>  retriveUser(@PathVariable Integer id){
        User user =  service.findUser(id);
        if(user == null)
            throw new UserNotFoundExcepiton("id: " + id);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUser());
        entityModel.add(link.withRel("all-users"));
        
        return entityModel;
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
