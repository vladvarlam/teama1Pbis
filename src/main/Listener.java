//package TemaCurs2;

public interface Listener {
    public void notifyList();

    public default void notifyWindowClosed() {
    }
}