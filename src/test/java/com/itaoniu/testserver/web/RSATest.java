package com.itaoniu.testserver.web;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.itaoniu.testserver.utils.RSAUtils;
import com.itaoniu.testserver.utils.RSAUtils.RSAKey;

public class RSATest {

	@Test
	public void rsa_test() throws Exception{
        HashMap<String, Object> map = RSAUtils.getKeys();  
        //生成公钥和私钥  
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");  
          
        //模  
        String modulus = publicKey.getModulus().toString(); 
        System.err.println("模："+modulus);  
        //公钥指数  
        String public_exponent = publicKey.getPublicExponent().toString();  
        System.err.println("公钥指数："+public_exponent);  
        //私钥指数 
        String private_exponent = privateKey.getPrivateExponent().toString();  
        System.err.println("私钥指数："+private_exponent);  
        //明文  
        String ming = "123456789"; 
        
        //使用模和指数生成公钥和私钥  
        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);  
        RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent); 
        
        //加密后的密文  
        String mi = RSAUtils.encryptByPublicKey(ming, publicKey);  
        System.err.println("密文："+mi);  
        
        //解密后的明文  
        ming = RSAUtils.decryptByPrivateKey(mi, privateKey);  
        System.err.println("明文："+ming);  
        
        
	}
	
	@Test
	public void keys(){
		Map<String, RSAKey> map = RSAUtils.getRSAKeys();
		RSAKey pub = map.get("public");
		RSAKey pri = map.get("private");
		System.err.println("【public】"+pub);
		System.err.println("【private】"+pri);
	}
}
