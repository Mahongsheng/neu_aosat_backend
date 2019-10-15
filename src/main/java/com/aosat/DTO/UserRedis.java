package com.aosat.DTO;

public class UserRedis {
    private String salt;
    private String pwdHansh;

    public UserRedis(String salt, String pwdHansh) {
        this.salt = salt;
        this.pwdHansh = pwdHansh;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPwdHansh() {
        return pwdHansh;
    }

    public void setPwdHansh(String pwdHansh) {
        this.pwdHansh = pwdHansh;
    }
}
