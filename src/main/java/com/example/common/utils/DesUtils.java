package com.example.common.utils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * 密码加密解密
 */
public class DesUtils {

    private static final String DES_ALGORITHM = "DES";

    private static final String KEY = "597289LC";

    /**
     * 加密
     *
     * @param data 要加密的数据
     * @return
     */
    public String encrypt(String data) {
        try {
            // 根据密钥生成密钥规范
            KeySpec keySpec = new DESKeySpec(KEY.getBytes());
            // 根据密钥规范生成密钥工厂
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
            // 根据密钥工厂和密钥规范生成密钥
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

            // 根据算法获得加密器
            Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
            // 初始化加密器，设置加密模式和算法
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // 加密数据
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            // 对加密后的数据进行Base64编码
            String password = Base64.getEncoder().encodeToString(encryptedData);
            return password;
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     *
     * @param data 要解密的数据
     * @return
     */
    public String decrypt(String data) {
        try {
            // 根据密钥生成密钥规范
            KeySpec keySpec = new DESKeySpec(KEY.getBytes());
            // 根据密钥规范生成密钥工厂
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
            // 根据密钥工厂和密钥规范生成密钥
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

            // 对加密后的数据进行Base64解码
            byte[] decodeData = Base64.getDecoder().decode(data);
            // 根据算法获得解密器
            Cipher cipher = Cipher.getInstance(DES_ALGORITHM);
            // 初始化解密器，设置解密模式和算法
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            //解密数据
            byte[] decryptedData = cipher.doFinal(decodeData);
            // 将解密后的数据转换为字符串
            String password = new String(decryptedData);
            return password;
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
