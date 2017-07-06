/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.encoder;

/**
 *
 * @author sf
 */
public class Encoder {
    public static final String ENCODING = "UTF-8";
    public static String encrypt(String x) throws Exception {
        java.security.MessageDigest digest = null;
        digest = java.security.MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(x.getBytes(ENCODING));
        return new String(digest.digest(), ENCODING);
    }
}
