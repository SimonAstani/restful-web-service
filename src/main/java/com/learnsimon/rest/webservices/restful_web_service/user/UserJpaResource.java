package com.learnsimon.rest.webservices.restful_web_service.user;

import com.learnsimon.rest.webservices.restful_web_service.repository.PostRepository;
import com.learnsimon.rest.webservices.restful_web_service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private UserDoaService service;

    private UserRepository repository;

    private PostRepository postRepository;

    //constructor injection
    public UserJpaResource(UserDoaService service, UserRepository repository, PostRepository postRepository){
        this.service = service;
        this.repository= repository;
        this.postRepository = postRepository;
    }



    @GetMapping("/jpa/users")
    public List<User>  retriveAllUser(){
        return repository.findAll();
    }


    //made certain chenages
    // localhost:8080/users
    // RepresenationMoel or EntityModel
    //  WebMvcLinkBuilder
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User>  retriveUser(@PathVariable Integer id){
        Optional<User> user =  repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundExcepiton("id: " + id);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUser());
        entityModel.add(link.withRel("all-users"));
        
        return entityModel;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrivePostsForAllUser(@PathVariable int id){
        Optional<User> user =  repository.findById(id);
        System.out.println("users" + user);
        if(user.isEmpty())
            throw new UserNotFoundExcepiton("id: " + id);
        return user.get().getPosts();

    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User savedUser = repository.save(user);
        System.out.println("sav=====" + savedUser);
        //location -header  /users/4 => uri
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();


    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForAllUser(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user =  repository.findById(id);
        System.out.println("users" + user +  user.get());
        if(user.isEmpty())
            throw new UserNotFoundExcepiton("id: " + id);
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }




}
