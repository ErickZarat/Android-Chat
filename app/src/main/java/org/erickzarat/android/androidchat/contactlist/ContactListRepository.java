package org.erickzarat.android.androidchat.contactlist;

/**
 * Created by erick on 22/06/16.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeToContactListEvents();
    void unsubscribeToContactListEvents();
    void changeConnectionStatus(boolean online);
}
