package com.minicare.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordUtil {
    public static String getHashedPassword(String password) {
        String hashedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashedPassword = new String(hash);
        }
        catch(NoSuchAlgorithmException n) {
            Logger logger = Logger.getLogger(PasswordUtil.class.getName());
            logger.log(Level.SEVERE, "Exception While Hashing Password" + n);
        }
        return hashedPassword;
    }
}
