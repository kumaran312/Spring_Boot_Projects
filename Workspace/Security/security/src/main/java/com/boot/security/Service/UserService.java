package com.boot.security.Service;

import com.boot.security.Dto.UserDto;
import com.boot.security.Model.User;
import com.boot.security.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService{

    User getUser(String username, String password);

    User save(UserDto userDto);
}
