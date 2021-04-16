package lendpal.model;

import java.time.Period;
import java.util.*;

public class LendPalModel {

    private final List<User> users = new ArrayList<>();

    /**
     * Key String is a LendPalItem's Id. HashMap is useful for quick lookups.
     */
    private final LendPalItemContainer availableItems = new LendPalItemContainer();

    public void addUser(User user) {
        users.add(user);
    }

    public void addNewUser(
            String firstName, String lastName, String email, String password, String passwordConfirm)
            throws IllegalArgumentException {
        if (containsUserWithEmail(email)) {
            throw new IllegalArgumentException("Bruker med epost " + email + " eksisterer allerede");
        } else if (!password.equals(passwordConfirm)) {
            throw new IllegalArgumentException("Passordene matcher ikke");
        }
        addUser(new User(firstName, lastName, email, password));
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

    // public boolean containsUser(User user) { return users.contains(user); }

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

    public User getUserByEmail(String email) {
        return (users.stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst())
                .orElse(null);
    }

    public boolean containsUserWithEmail(String email) {
        return (users.stream().anyMatch(p -> p.getEmail().equals(email)));
    }

    public boolean checkUserCredentials(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null) {
            return user.checkIfPasswordIsCorrect(password);
        } else { return false; }
    }

    public User getItemHolder(String itemId) {
        return (users.stream()
                .filter(p -> p.hasItem(itemId))
                .findFirst())
                .orElse(null);
    }

    public LendPalItem getAvailableItem(String itemId) {
        return availableItems.getItem(itemId);
    }

    public Period getDefaultLendTime(String itemId) {
        return availableItems.getItem(itemId).getDefaultLendTime();
    }

    /**
     * returns all items lent by specified user
     * @param userId
     * @return
     */
    public LendPalItemContainer getAllUnavailableItems(String userId) {
        return Objects.requireNonNull((users.stream()
                .filter(p -> p.getId().equals(userId))
                .findFirst())
                .orElse(null)).getLentItems();
    }

    public LendPalItemContainer getAllUnavailableItems() {
        LendPalItemContainer unavailableItems = new LendPalItemContainer();
        for (User user : users) {
            unavailableItems.addItems(user.getLentItems().getItems());
        }
        return unavailableItems;
    }

    public void lendItem(String userId, LendPalItem item) {
        try {
            if (availableItems.containsItem(item.getId())) {
                availableItems.removeItem(item);
            }
            this.getUser(userId).getLentItems().lendItemForDefaultTime(item);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Either the user or the item is not registered in the model.");
        }
    }

    /**
     * Item must be listed in availableItems
     * @param userId
     * @param itemId
     * @throws IllegalArgumentException if item is not listed in availableItems
     */
    public void lendAvailableItem(String userId, LendPalItem item) {
        try {
            if (!availableItems.containsItem(item.getId())) {
                throw new IllegalArgumentException("Item is not present in availableItems");
            }
            this.getUser(userId).getLentItems().lendItemForDefaultTime(item);
            this.availableItems.removeItem(item);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Either the user or the item is not registered in the model.");
        }
    }

    public boolean isItemAvailable(String itemId) {
        return availableItems.containsItem(itemId);
    }

    public void addAvailableItem(LendPalItem item) {
        availableItems.addItem(item);
    }

    public void removeAvailableItem(LendPalItem item) {
        availableItems.removeItem(item);
    }

    public LendPalItemContainer getAllAvailableItems() {
        return this.availableItems;
    }

    public LendPalItemContainer getAllItems() {
        LendPalItemContainer container = new LendPalItemContainer();
        container.addItems(getAllAvailableItems().getItems());
        container.addItems(getAllUnavailableItems().getItems());
        return container;
    }


}
