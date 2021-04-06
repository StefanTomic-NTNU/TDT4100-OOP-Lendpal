package lendpal.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Hex;

/**
 * Users may lend Items using LendPal
 */
public class User {

    enum Privileges {
        NONMEMBER,
        MEMBER,
        MODERATOR,
        ADMINISTRATOR
    }

    private String firstName;
    private String lastName;
    private String email;

    /**
     * This valid email pattern is not 100% correct but is sufficient.
     */
    private final static Pattern validEmailPattern = Pattern.compile(
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    /**
     * Passwords are hashed using salts during construction of User objects.
     */
    private String password;
    private byte[] salt;
    private Privileges privileges;

    public User(String firstName, String lastName, String email, String password, Privileges privileges) {
        if (!this.verifyEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " is invalid.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = generateSalt();
        this.password = hashPassword(password, this.salt);
        this.privileges = privileges;
    }

    public User(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Privileges.MEMBER);
    }

    private boolean verifyEmail(String email) { return validEmailPattern.matcher(email).matches(); }

    private static byte[] generateSalt() {
        byte[] bytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    private static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(salt);
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Privileges getPrivileges() { return privileges; }

    public void setPrivileges(Privileges privileges) { this.privileges = privileges; }
}
