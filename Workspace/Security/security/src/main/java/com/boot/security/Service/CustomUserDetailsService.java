package com.boot.security.Service;


import com.boot.security.Model.User;
import com.boot.security.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);
            if (user == null){
                throw new UsernameNotFoundException("User not found");
            }
        return new CustomUserDetails(user);
    }

    public String getRoleId(String username) {
        // Fetch the user from the database using the username
        User user = userRepo.findByEmail(username);

        // Get the roleId of the user

        return user.getRoleid();
    }

}
