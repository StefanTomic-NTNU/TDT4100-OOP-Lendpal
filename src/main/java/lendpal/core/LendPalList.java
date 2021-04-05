package lendpal.core;

import java.util.*;

public class LendPalList implements Iterable<LendPalItem> {

    private Set<LendPalItem> lendPalItems = new TreeSet<>(new LenPalItemComparator());
    private Collection<LendPalListListener> lendPalListListeners = new ArrayList<>();
    private User user;

    public LendPalList(User user) { this.user = user; }

    public Set<LendPalItem> getLendPalItems() { return lendPalItems; }

    public void setLendPalItems(Set<LendPalItem> lendPalItems) {
        this.lendPalItems = lendPalItems;
    }

    public Collection<LendPalListListener> getLendPalListListeners() {
        return lendPalListListeners;
    }

    public void setLendPalListListeners(Collection<LendPalListListener> lendPalListListeners) {
        this.lendPalListListeners = lendPalListListeners;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Iterator<LendPalItem> iterator() {
        return lendPalItems.iterator();
    }
}
