package org.erickzarat.android.androidchat.lib;

/**
 * Created by erick on 21/06/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
