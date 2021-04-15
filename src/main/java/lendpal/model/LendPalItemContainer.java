package lendpal.model;

import org.jetbrains.annotations.NotNull;

import java.time.temporal.TemporalAmount;
import java.util.*;

public class LendPalItemContainer implements Iterable<LendPalItem> {

    private List<LendPalItem> items = new ArrayList<>();

    /**
     * Transient because listeners are instantiated each time model is used.
     * Thus there is no need to store them in file.
     */
    private transient Collection<LendPalItemContainerListener> listeners = new ArrayList<>();

    public void addItem(LendPalItem item) {
        this.items.add(item);
    }

    public void addItems(LendPalItem... items) {
        this.items.addAll(Arrays.asList(items));
    }

    public void lendItem(LendPalItem item, TemporalAmount time) {
        if (!items.contains(item)) {
            item.lendFor(time);
            addItem(item);
        }
    }

    public void lendItemForDefaultTime(LendPalItem item) {
        if (!items.contains(item)) {
            item.lendForDefaultTime();
            addItem(item);
        }
    }

    public void removeItem(LendPalItem item) {
        this.items.remove(item);
    }

    public void setItems(List<LendPalItem> items) {
        this.items = items;
    }

    public List<LendPalItem> getItems() {
        return items;
    }

    public LendPalItem getItem(String itemId) {
        return items.stream()
                .filter(p -> p.getId().equals(itemId))
                .findFirst()
                .orElse(null);
    }

    public boolean containsItem(String itemId) {
        return items.stream().allMatch(p -> p.getId().equals(itemId));
    }

    public void addListener(LendPalItemContainerListener listener) {
        this.listeners.add(listener);
    }

    public void fireContainerChanged() {
        for (LendPalItemContainerListener listener : listeners) {
            listener.containerChanged(this);
        }
    }

    public Collection<LendPalItemContainerListener> getListeners() {
        return listeners;
    }

    @NotNull
    @Override
    public Iterator<LendPalItem> iterator() {
        return items.iterator();
    }
}
