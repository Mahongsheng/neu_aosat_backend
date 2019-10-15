package com.aosat.Util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * AES 加密方法，是对称的密码算法(加密与解密的密钥一致)，这里使用最大的 256 位的密钥
 */
public class AESUtil {

    private static final String KEY = "g89Q+5yGKriABqh6vG5Y2DYRmyVY590hoqvJYvghG9Q=";
    /**
     * 获得一个 密钥长度为 256 位的 AES 密钥，
     *由于使用固定密钥，此方法为一次性方法
     * @return 返回经 BASE64 处理之后的密钥字符串
     */
    private static String getStrKeyAES() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 密钥生成器，生成AES密钥
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        // 加强版的random生成器，由于random类为伪随机，可预测性强，故采用此类
        SecureRandom secureRandom = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
        // 生成256位密钥，这里可以是 128、192、256、越大越安全，并传入随机数源
        keyGen.init(256, secureRandom);
        // 生成密钥
        SecretKey secretKey = keyGen.generateKey();
        // BASE64编码
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * ？我也不知道为啥非要用BASE64编码处理，再分出来一个方法写生成key
     * 但既然他这么写了我就先这么用着吧
     * 将使用 Base64 编码后的字符串类型的 secretKey 转为 SecretKey
     *
     * @return SecretKey
     */
    private static SecretKey strKey2SecretKey() {
        byte[] bytes = Base64.getDecoder().decode(AESUtil.KEY);
        // 根据给定的字节数组构造一个密钥
        return new SecretKeySpec(bytes, "AES");
    }

    /**
     * 加密
     *
     * @param content   待加密内容
     * @return 加密后的密文 byte[]
     */
    public static byte[] encryptAES(byte[] content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 实现AES转换的Cipher对象
        Cipher cipher = Cipher.getInstance("AES");
        // 将对象初始化为加密模式并传入密钥
        cipher.init(Cipher.ENCRYPT_MODE, strKey2SecretKey());
        // 执行加密并返回
        return cipher.doFinal(content);
    }

    /**
     * 解密
     *
     * @param content   待解密内容
     * @return 解密后的明文 byte[]
     */
    public static byte[] decryptAES(byte[] content) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 实现AES转换的Cipher对象
        Cipher cipher = Cipher.getInstance("AES");
        // 将对象初始化为解密模式并传入密钥
        cipher.init(Cipher.DECRYPT_MODE, strKey2SecretKey());
        // 执行解密并返回
        return cipher.doFinal(content);
    }

    // 生成随机盐值
    public static String generateSalt() {
        Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[8];
        ranGen.nextBytes(aesKey);
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < aesKey.length; i++) {
            String hex = Integer.toHexString(0xff & aesKey[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return String.valueOf(hexString);
    }
    // 测试
    public static void main(String[] args) {
        // 待加密的字符串
        String content = "abcdefg789+-*+=";
        //加盐
        String salt = generateSalt();
        String all = content.concat(salt);
        System.out.println("明文数据为：" + content);
        System.out.println("加盐数据为：" + all);

        try {
//            // 获得经 BASE64 处理之后的 AES 密钥
//            String strKeyAES = AESUtil.getStrKeyAES();
//            System.out.println("经BASE64处理之后的密钥：" + strKeyAES);
//             将 BASE64 处理之后的 AES 密钥转为 SecretKey
//            SecretKey secretKey = AESUtil.strKey2SecretKey(strKeyAES);
            // 加密数据
            byte[] encryptAESbytes = AESUtil.encryptAES(all.getBytes(StandardCharsets.UTF_8));
            System.out.println("加密后的数据经 BASE64 处理之后为：" + Base64.getEncoder().encodeToString(encryptAESbytes));            // 解密数据
            String decryptAESStr = new String(AESUtil.decryptAES(encryptAESbytes), StandardCharsets.UTF_8);
            System.out.println("解密后的数据为：" + decryptAESStr);

            String input = content;
            input = content.concat(salt);

            if (input.equals(decryptAESStr)) {
                System.out.println("测试通过！");
            } else {
                System.out.println("测试未通过！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

