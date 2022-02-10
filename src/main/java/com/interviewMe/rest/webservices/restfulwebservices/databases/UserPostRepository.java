package com.interviewMe.rest.webservices.restfulwebservices.databases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<Posts, Integer> {


}
