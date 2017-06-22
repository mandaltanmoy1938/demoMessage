package com.example.demoMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tanmoy Mandal on 12/27/2016.
 */
@Service
public class UserService {
//    @Autowired
//    UserRepository userRepository;

    @Autowired
    UsersDAO usersDAO;

    public String save(UserEntity userEntity){
//        userRepository.save(userEntity);
        return usersDAO.save(userEntity);
    }

    public List<UserEntity> getEntityList(){
        return usersDAO.getEntityList();
//        return userRepository.findAll();
    }
}
