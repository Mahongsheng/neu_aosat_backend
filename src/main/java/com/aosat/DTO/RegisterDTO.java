package com.aosat.DTO;

public class RegisterDTO {
    private String realName;
    private String userName;
    private String password;

    public RegisterDTO(String realName, String userName, String password) {
        this.realName = realName;
        this.userName = userName;
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
