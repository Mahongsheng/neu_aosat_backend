package com.aosat.Service.Interface;

import com.aosat.DTO.LoginDTO;
import com.aosat.DTO.RegisterDTO;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public interface LoginInterface {
    boolean register(RegisterDTO registerDTO) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    boolean validate(String userName);

    boolean login(LoginDTO loginDTO);
}


