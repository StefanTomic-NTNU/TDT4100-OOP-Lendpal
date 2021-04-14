package lendpal.model;

import org.jetbrains.annotations.NotNull;

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

    public void removeItem(LendPalItem item) {
        this.items.remove(item);
    }

    public List<LendPalItem> getItems() {
        return items;
    }

    public void setItems(List<LendPalItem> items) {
        this.items = items;
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
