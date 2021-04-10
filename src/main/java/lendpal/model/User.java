package lendpal.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Hex;

/**
 * Users may lend Items using LendPal
 */
public class User {

    private enum Privileges {
        NONMEMBER,
        MEMBER,
        MODERATOR,
        ADMINISTRATOR

        }

    /**
     * This valid email pattern is not 100% correct but is sufficient.
     */
    private final static Pattern validEmailPattern = Pattern.compile(
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private String firstName;
    private String lastName;
    private String email;

    /**
     * Id is generated using UUID in this iteration of the program.
     * The id is used as key in the LendPalModel HashMap. It is not
     * a int since it is easier to convert a UUID to String rather
     * than int.
     */
    private String id;

    /**
     * Passwords are hashed using salts during construction of User objects.
     */
    private String password;
    private byte[] salt;
    private Privileges privileges;
    private Map<String, ZonedDateTime> lentItems = new HashMap<>();
    private Collection<UserListener> listeners = new ArrayList<>();

    public User(String id, String firstName, String lastName, String email, String password, Privileges privileges) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.salt = generateSalt();
        this.setPassword(password, salt);
        this.privileges = privileges;
        this.id = id;
    }

    public User(String firstName, String lastName, String email, String password, Privileges privileges) {
        this(UUID.randomUUID().toString(), firstName, lastName, email, password, Privileges.MEMBER);
    }

    public User(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Privileges.MEMBER);
    }

    /**
     * TODO: REMOVE
     * Used for testing purposes only
     * @param firstName
     */
    public User(String firstName) {
        this(firstName, "Etternavn", "epost@epost.com", "tullepassord");
    }

    public static boolean verifyEmail(String email) { return validEmailPattern.matcher(email).matches(); }

    /**
     * Checks if a unhashed password is valid.
     * @param password Unhashed password
     * @return True if password is valid, false otherwise
     */
    public static boolean verifyPassword(String password) {
        return (password.length() > 5);
    }

    public static boolean verifyName(String password) {
        return (password.length() > 1);
    }

    /**
     * Generates a random salt of size 16 bytes.
     * @return Salt as byte array
     */
    private static byte[] generateSalt() {
        byte[] bytes = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    /**
     * Hashes password using SHA-256 and given salt
     * @param password Unhashed password
     * @param salt as byte array
     * @return Hashed password as String
     */
    public static String hashPassword(String password, byte[] salt) {
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

    public boolean checkIfPasswordIsCorrect(String password) {
        String hashedPassword = hashPassword(password, this.salt);
        return hashedPassword.equals(this.password);
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        if (!verifyName(firstName)) {
            throw new IllegalArgumentException("First name " + firstName + " is invalid.");
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        if (!verifyName(lastName)) {
            throw new IllegalArgumentException("Last name " + lastName + " is invalid.");
        } else {
            this.lastName = lastName;
        }
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (!verifyEmail(email)) {
            throw new IllegalArgumentException("Email " + email + " is invalid.");
        } else {
            this.email = email;
        }
    }

    public byte[] getSalt() { return salt; }

    public void setSalt(byte[] salt) { this.salt = salt; }

    public String getPassword() { return password; }

    public void setPassword(String password, byte[] salt) {
        if (!verifyPassword(password)) {
            throw new IllegalArgumentException("Password is invalid.");
        } else {
            this.password = hashPassword(password, salt);
        }
    }

    public Privileges getPrivileges() { return privileges; }

    public void setPrivileges(Privileges privileges) { this.privileges = privileges; }

    public String getId() {
        return this.id;
    }

    public boolean idEquals(User other) { return this.id.equals(other.id); }

    public boolean hasItem(String itemId) {
        return lentItems.containsKey(itemId);
    }

    public Map<String, ZonedDateTime> getLentItems() {
        return lentItems;
    }

    public void lendItem(String itemId, ZonedDateTime returnDate) {
        lentItems.put(itemId, returnDate);
    }

    public void addUserListener(UserListener listener) {
        listeners.add(listener);
    }

    public void removeUserListener(UserListener listener) {
        listeners.remove(listener);
    }

    protected void fireUserChanged() {
        for (UserListener listener : listeners) {
            listener.userChanged(this);
        }
    }

}
