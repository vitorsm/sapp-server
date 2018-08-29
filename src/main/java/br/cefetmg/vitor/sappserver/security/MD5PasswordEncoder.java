package br.cefetmg.vitor.sappserver.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
public class MD5PasswordEncoder implements PasswordEncoder {

	private static final Logger LOGGER = Logger.getLogger(MD5PasswordEncoder.class.toString());
	

	@Override
	public String encode(CharSequence charSequence) {
		String encPass = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(charSequence.toString().getBytes());
			byte[] b64 = Base64.encodeBase64(digest);
			encPass = new String(b64);
			encPass = encPass.replaceAll("=", "");
		}catch(Exception ex){
			LOGGER.info("An exception trying to encode a password: " + ex.getMessage());
		}
		return encPass;
	}
	
	// Funcao copiada do Sistema Academico por motivo de preguiça de implementar uma melhor
	public String encode(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
        
        while (md5.length() < 32) {
        	md5 = "0"+md5;
        }
        
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
	}
	
	@Override
	public boolean matches(CharSequence charSequence, String s) {
		return encode(charSequence).equals(s);
	}

}
