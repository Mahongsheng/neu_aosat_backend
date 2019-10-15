package com.aosat.Service;

import com.aosat.DTO.LoginDTO;
import com.aosat.DTO.RegisterDTO;
import com.aosat.DTO.UserRedis;
import com.aosat.Dao.UserMapper;
import com.aosat.Pojo.User;
import com.aosat.Service.Interface.LoginInterface;
import com.aosat.Util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Service
public class LoginService implements LoginInterface {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public boolean register(RegisterDTO registerDTO) {

        try {
            String password = registerDTO.getPassword();
            String salt = AESUtil.generateSalt();
            String addSalt = password.concat(AESUtil.generateSalt());
            byte[] pwdHash = AESUtil.encryptAES(addSalt.getBytes());
            String hash = new String(pwdHash);
            User user = new User(registerDTO.getUserName(), salt, hash, registerDTO.getRealName());
            synchronized (this) {
                userMapper.insertSelective(user);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean validate(String userName) {
        return userMapper.getUser(userName) == null;
    }

    @Override
    public boolean login(LoginDTO loginDTO) {

        //字符串的序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        //从redis缓存中获取列表
        UserRedis userRedis = (UserRedis) redisTemplate.opsForValue().get(loginDTO.getUserName());
        //未读取则从数据库中读取并载入缓存
        if (null == userRedis) {
            User user = userMapper.getUser(loginDTO.getUserName());
            userRedis = new UserRedis(user.getSalt(), user.getPwdhash());
            redisTemplate.opsForValue().set(loginDTO.getUserName(), userRedis);
        }
        String content = loginDTO.getPassword().concat(userRedis.getSalt());
        try {
            byte[] ciphertext = AESUtil.encryptAES(content.getBytes());
            String result = new String(ciphertext);
            if (result.equals(userRedis.getPwdHansh())) {
                return true;
            }


        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return false;
    }


}



