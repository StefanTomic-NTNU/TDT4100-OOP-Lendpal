package lendpal.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.*;

public class LendPalList {

    //TODO: Make sure User is synchronised with LendPalModel

    private Map<LendPalItem, ZonedDateTime> lentItems = new HashMap<LendPalItem, ZonedDateTime>();
    private Collection<LendPalListListener> listeners = new ArrayList<>();
    private User user;

    public LendPalList(User user) { this.user = user; }

    public LendPalList(User user, LendPalItem... items) {
        this(user);
        putItems(items);
    }

    public Map<LendPalItem, ZonedDateTime> getLendPalItems() { return lentItems; }

    public void setLendPalItems(Map<LendPalItem, ZonedDateTime> lentItems) { this.lentItems = lentItems; }

    public void putItem(LendPalItem item, ZonedDateTime returnDate) { this.lentItems.put(item, returnDate); }

    public void putItem(LendPalItem item, TemporalAmount lendTime) {
        ZonedDateTime returnDate = ZonedDateTime.now().plus(lendTime);
        this.putItem(item, returnDate);
    }

    public void putItem(LendPalItem item) {
        long lendTime = item.getDefaultDaysLent();
        this.putItem(item, ZonedDateTime.now().plusHours(lendTime));
    }

    public void putItems(LendPalItem... items) {
        if (items.length > 0) {
            for (LendPalItem item : items) { this.putItem(item); }
        } else { throw new IllegalArgumentException("PutItems must take at least one LendPalItem as input."); }
    }

    public boolean containsItem(LendPalItem item) {
        return lentItems.containsKey(item);
    }

    public void extendLendTime(LendPalItem item, TemporalAmount lendTime) {
        if (!lentItems.containsKey(item)) {
            throw new IllegalArgumentException("No such item is present in LendPalList.");
        }
        ZonedDateTime previousReturnDate = lentItems.get(item);
        ZonedDateTime extendedReturnDate = previousReturnDate.plus(lendTime);
        this.lentItems.put(item, extendedReturnDate);
    }

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
