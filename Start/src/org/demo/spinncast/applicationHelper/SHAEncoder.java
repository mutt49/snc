package org.demo.spinncast.applicationHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHAEncoder {
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String passwordToHash = "password";
        String salt = "admin";
        String securePassword = "";
         
        securePassword = get_SHA_512_SecurePassword(passwordToHash, salt);
        System.out.println(securePassword);
    }
 
    public static String get_SHA_512_SecurePassword(String passwordToHash, String salt)
    {
        String generatedPassword = null;
        try {
        	MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
    //Add salt
    private static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
}