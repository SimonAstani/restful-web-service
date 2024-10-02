package com.learnsimon.rest.webservices.restful_web_service.repository;

import com.learnsimon.rest.webservices.restful_web_service.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
