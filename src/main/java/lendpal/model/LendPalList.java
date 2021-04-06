package lendpal.model;

import java.time.LocalDateTime;
import java.util.*;

public class LendPalList {

    private Map<LendPalItem, LocalDateTime> lentItems = new HashMap<>();
    private Collection<LendPalListListener> listeners = new ArrayList<>();
    private User user;

    public LendPalList(User user) { this.user = user; }

    public Map<LendPalItem, LocalDateTime> getLendPalItems() { return lentItems; }

    public void setLendPalItems(Map<LendPalItem, LocalDateTime> lentItems) { this.lentItems = lentItems; }

    public void addItem(LendPalItem item, LocalDateTime returnDate) { this.lentItems.put(item, returnDate); }

    public void removeItem(LendPalItem item) { this.lentItems.remove(item); }

    public Collection<LendPalListListener> getLendPalListListeners() { return listeners; }

    public void setLendPalListListeners(Collection<LendPalListListener> lendPalListListeners) {
        this.listeners = lendPalListListeners;
    }

    public void addListener(LendPalListListener listener) { this.listeners.add(listener); }

    public void removeListener(LendPalListListener listener) { this.listeners.remove(listener); }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder(user.getFirstName());
        returnString.append(": ");
        for (LendPalItem lentItem : lentItems.keySet()) {
            returnString.append("\n");
            returnString.append(lentItem.toString());
        }
        return returnString.toString();
    }
}
