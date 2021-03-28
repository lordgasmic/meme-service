package com.lordgasmic.==PACKAGE_NAME==.service;

import com.lordgasmic.==PACKAGE_NAME==.entity.UserEntity;
import com.lordgasmic.==PACKAGE_NAME==.model.User;
import com.lordgasmic.==PACKAGE_NAME==.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class LoginService {

    @Autowired
    private LoginRepository repository;

    public boolean login(final User user) {
        final String username = sanitize(user.getUsername());
        final UserEntity entity = repository.findByUsername(username);
        if (entity != null && entity.isEnabled()) {
            final String pwconcat = user.getPassword() + entity.getSalt();
            final String hashedPassword = composePassword(pwconcat);
            return hashedPassword.equals(entity.getPassword());
        }

        return false;
    }

    public boolean addUser(final User user) {
        final String username = sanitize(user.getUsername());
        final UserEntity entity = repository.findByUsername(username);
        if (entity == null) {
            final String salt = generateSalt();
            final String pwconcat = user.getPassword() + salt;
            final String hashedPassword = composePassword(pwconcat);

            final UserEntity newUser = new UserEntity(username, hashedPassword, salt, true);
            repository.save(newUser);
        }

        return false;
    }

    private static String sanitize(final String string) {
        return string.chars()
                     .mapToObj(Character::toString)
                     .filter(c -> c.matches("[a-zA-Z0-9]"))
                     .reduce((s1, s2) -> s1 + s2)
                     .orElse("");
    }

    private static String bytesToHex(final byte[] hash) {
        final StringBuilder hexString = new StringBuilder();

        for (final byte b : hash) {
            final String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private static String composePassword(final String pwconcat) {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException();
        }
        final byte[] password = md.digest(pwconcat.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(password);
    }

    private static String generateSalt() {
        final byte[] bytes = new byte[32];
        final Random r = new Random();
        r.nextBytes(bytes);
        return bytesToHex(bytes);
    }
}
