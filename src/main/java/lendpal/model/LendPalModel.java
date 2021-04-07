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

    public void addItemToLendPalList(User user, LendPalItem item) {
        LendPalList list = this.userLendPalListMap.get(user);
        list.putItems(item);
    }

}
