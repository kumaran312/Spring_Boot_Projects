package com.boot.security.Model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String roleid = "USER";
    private String fullname;
   
    public User(Long id, String email, String password, String fullname, String roleid) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.roleid = roleid;
		this.fullname = fullname;
	}

	public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleid=" + roleid +
                ", fullname='" + fullname + '\'' +
                '}';
    }

    public String setUsername(String username) {

        username=this.getEmail();

        return username;
    }
}
