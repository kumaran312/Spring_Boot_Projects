package com.boot.security.Service;

import com.boot.security.Dto.UserDto;
import com.boot.security.Model.User;
import com.boot.security.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    public boolean hasUsers() {
        return userRepo.count() > 0;
    }

    @Override
    public User getUser(String username, String password){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleid("USER");

        return user;
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(null, userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()),userDto.getFullname(),userDto.getRoleid());
        return userRepo.save(user);
    }
}
