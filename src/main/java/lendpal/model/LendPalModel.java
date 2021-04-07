package lendpal.model;

import java.util.HashMap;
import java.util.Map;

public class LendPalModel {

    private final Map<User, LendPalList> userLendPalListMap = new HashMap<>();

    public void mapUserToList(User user, LendPalList list) {
        this.userLendPalListMap.put(user, list);
    }

    public void addNewUser(User user) {
        LendPalList list = new LendPalList(user);
        mapUserToList(user, list);
    }

    public boolean containsUser(User user) { return userLendPalListMap.containsKey(user); }

    public LendPalList getLendPalList(User user) {
        return userLendPalListMap.get(user);
    }

    public void addItemToLendPalList(User user, LendPalItem item) {
        try {
            LendPalList list = this.userLendPalListMap.get(user);
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
