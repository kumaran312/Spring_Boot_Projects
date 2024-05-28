package com.boot.security.Dto;

public class UserDto {

    private String email;
    private String password;
    private String roleid = "USER";
    private String fullname;

    public UserDto() {
        super();
    }

    public UserDto(String email, String password, String roleid, String fullname) {
        super();
        this.email = email;
        this.password = password;
        this.roleid = roleid;
        this.fullname = fullname;
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

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String setUsername(String username) {

        username=this.getEmail();

        return username;
    }

}
