package com.creasy;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptingTest {

    @Test
    public void testMD5() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("123456".getBytes());
        byte[] digest = md.digest();
        BigInteger no = new BigInteger(1, digest);

        // Convert message digest into hex value
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        System.out.println(hashtext);
        //e10adc3949ba59abbe56e057f20f883e
        //e10adc3949ba59abbe56e057f20f883e
    }

}
