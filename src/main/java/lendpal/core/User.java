package lendpal.core;

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
     * TO BE HASHED. USED FOR LOGIN
     */
    private String password;
    private Privileges privileges;

}
