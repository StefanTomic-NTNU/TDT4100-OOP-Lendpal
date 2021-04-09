package lendpal.model;

import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.*;

public class LendPalModel {

    private final Set<User> users = new HashSet<>();

    /**
     * Key String is a LendPalItem's Id. HashMap is useful for quick lookups.
     */
    private final Map<String, LendPalItem> items = new HashMap<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * TODO:REMOVE METHOD
     * Used for testing purposes only
     * @param firstName first name of user
     */
    public void addNewUserWithName(String firstName) {
        addUser(new User("Fornavn"));
    }

    public boolean containsUser(User user) { return users.contains(user); }

    public boolean containsUser(String userId) {
        return (users.stream()
                .anyMatch(p -> p.getId().equals(userId)));
    }

    public User getUser(String userId) {
        return (users.stream()
                .filter(p -> p.getId().equals(userId))
                .findFirst())
                .orElse(null);
    }

    public User getItemHolder(String itemId) {
        return (users.stream()
                .filter(p -> p.hasItem(itemId))
                .findFirst())
                .orElse(null);
    }

    public LendPalItem getItem(String itemId) {
        return items.get(itemId);
    }

    public Period getDefaultLendTime(String itemId) {
        return items.get(itemId).getDefaultLendTime();
    }

    public ZonedDateTime getReturnDateFromNow(String itemId) {
        return ZonedDateTime.now().plus(items.get(itemId).getDefaultLendTime());
    }

    public Map<String, ZonedDateTime> getLentItems(String userId) {
        return Objects.requireNonNull((users.stream()
                .filter(p -> p.getId().equals(userId))
                .findFirst())
                .orElse(null)).getLentItems();
    }

    public void lendItem(String userId, String itemId) {
        try {
            this.getUser(userId).lendItem(itemId, getReturnDateFromNow(itemId));
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Either the user or the item is not registered in the model.");
        }
    }

    public boolean isItemLent(String itemId) {
        return (users.stream()
                .anyMatch(p -> p.hasItem(itemId)));
    }

    public void addItem(LendPalItem item) {
        items.put(item.getId(), item);
    }

    public void removeItem(LendPalItem item) {
        items.remove(item.getId());
    }


}
