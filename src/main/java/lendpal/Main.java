package lendpal;

import lendpal.model.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("Stefan", "Tomic", "stefan@epost.com", "tullepassord");
        System.out.println(user.getPassword());
    }
}
