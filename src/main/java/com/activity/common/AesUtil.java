package com.activity.common;

import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密工具
 */
public class AesUtil {

    /**
     * 秘钥(需要前端和后端保持一致)
     */
    public final static String AES_KEY = "www.alifenga.xyz";

    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return StrUtil.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) {
        try {
            return base64Encode(aesEncryptToBytes(content, encryptKey));
        } catch (Exception e) {
            throw new BusinessException(ErrorEnum.E901);
        }

    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes, "UTF-8");
    }


    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) {
        try {
            return StrUtil.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
        } catch (Exception e) {
            throw new BusinessException(ErrorEnum.E900);
        }

    }

    /**
     * 测试
     * 前端js将参数加密提交到后台如何解密
     * 首先获取服务端的私钥:将客户端的公钥加密后获得的结果
     * 通过服务端的私钥和客户端传递的加密字符串即可实现解密
     */
    public static void main(String[] args) throws Exception {
        String content = "456";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + AES_KEY);
        String encrypt = aesEncrypt(content, AES_KEY);
        System.out.println("加密后：" + encrypt);
        String decrypt = aesDecrypt("SQakXYz8jkq/zvnk6qnhZQ==", AES_KEY);
        System.out.println("解密后：" + decrypt);
    }
}
