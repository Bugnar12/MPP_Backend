package org.backendspring_boot.backendspring_boot.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtils {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] salt = createSalt();
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        return bytesToHex(hashedPassword);
    }

    private static byte[] createSalt() {
        byte[] bytes = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }

    private static String bytesToHex(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes)
        {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
