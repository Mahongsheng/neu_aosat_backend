package com.aosat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Resource
//    private ValueOperations<String,Object> valueOperations;
//
//    @Autowired
//    private HashOperations<String, String, Object> hashOperations;
//
//    @Autowired
//    private ListOperations<String, Object> listOperations;
//
//    @Autowired
//    private SetOperations<String, Object> setOperations;
//
//    @Autowired
//    private ZSetOperations<String, Object> zSetOperations;
//
////    @Resource
////    private RedisService redisService;
////
////    @Test
////    public void testObj() throws Exception{
////        UserVO UserVO = new UserVO();
////        UserVO.setAddress("上海");
////        UserVO.setName("测试dfas");
////        UserVO.setAge(123);
////        ValueOperations operations = redisTemplate.opsForValue();
////        redisService.expireKey("name",20, TimeUnit.SECONDS);
////        String key = RedisKeyUtil.getKey(com.aosat.VOJO.UserVO.Table,"name",UserVO.getName());
////        UserVO vo = (UserVO) operations.get(key);
////        System.out.println(vo);
////    }
////
////    @Test
////    public void testValueOption( )throws  Exception{
////        UserVO UserVO = new UserVO();
////        UserVO.setAddress("上海");
////        UserVO.setName("jantent");
////        UserVO.setAge(23);
////        valueOperations.set("test",UserVO);
////
////        System.out.println(valueOperations.get("test"));
////    }
////
////    @Test
////    public void testSetOperation() throws Exception{
////        UserVO UserVO = new UserVO();
////        UserVO.setAddress("北京");
////        UserVO.setName("jantent");
////        UserVO.setAge(23);
////        UserVO aUserVO = new UserVO();
////        aUserVO.setAddress("n柜昂周");
////        aUserVO.setName("antent");
////        aUserVO.setAge(23);
////        setOperations.add("user:test",UserVO,aUserVO);
////        Set<Object> result = setOperations.members("user:test");
////        System.out.println(result);
////    }
////
////    @Test
////    public void HashOperations() throws Exception{
////        UserVO UserVO = new UserVO();
////        UserVO.setAddress("北京");
////        UserVO.setName("jantent");
////        UserVO.setAge(23);
////        hashOperations.put("hash:user",UserVO.hashCode()+"",UserVO);
////        System.out.println(hashOperations.get("hash:user",UserVO.hashCode()+""));
////    }
////
////    @Test
////    public void  ListOperations() throws Exception{
////        UserVO UserVO = new UserVO();
////        UserVO.setAddress("北京");
////        UserVO.setName("jantent");
////        UserVO.setAge(23);
//////        listOperations.leftPush("list:user",UserVO);
//////        System.out.println(listOperations.leftPop("list:user"));
////        // pop之后 值会消失
////        System.out.println(listOperations.leftPop("list:user"));
////    }
}
