package com.learnsimon.rest.webservices.restful_web_service.repository;

import com.learnsimon.rest.webservices.restful_web_service.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
