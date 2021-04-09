package lendpal.model;

import java.util.HashMap;
import java.util.Map;

public class LendPalModel {

    /**
     * String is a User's UUID
     */
    private final Map<String, LendPalList> userLendPalListMap = new HashMap<>();

    public void mapUserToList(User user, LendPalList list) {
        this.userLendPalListMap.put(user.getId(), list);
        list.setUser(user);
    }

    public void addNewUser(User user) {
        LendPalList list = new LendPalList(user);
        mapUserToList(user, list);
    }

    /**
     * TODO:REMOVE METHOD
     * Used for testing purposes only
     * @param firstName first name of user
     */
    public void addNewUserWithName(String firstName) {
        addNewUser(new User("Fornavn"));
    }

    public boolean containsUser(User user) { return userLendPalListMap.containsKey(user.getId()); }

    public User getUser(String userId) {
        return userLendPalListMap.get(userId).getUser();
    }

    public LendPalItem getItem(String itemId) {
        return (userLendPalListMap.values().stream()
                .filter(p -> p.(itemId))
                .findFirst()
                .get());
    }

    public LendPalList getLendPalList(User user) {
        return userLendPalListMap.get(user.getId());
    }

    public void addItemToLendPalList(User user, LendPalItem item) {
        try {
            LendPalList list = this.userLendPalListMap.get(user.getId());
            list.putItems(item);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("User is not registered in model.");
        }
    }

    public boolean containsItem(LendPalItem item) {
        return (userLendPalListMap.values().stream().anyMatch(p -> p.containsItem(item)));
    }

    public LendPalList getLendPalList(LendPalItem item) {
        if (containsItem(item)) {
            return (userLendPalListMap.values().stream()
                    .filter(p -> p.containsItem(item))
                    .findFirst()
                    .get());
        } else {
            return null;
        }
    }

}
