package com.example.demoMessage;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Tanmoy Mandal on 12/27/2016.
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

}
