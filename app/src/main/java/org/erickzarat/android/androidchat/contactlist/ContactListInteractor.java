package org.erickzarat.android.androidchat.contactlist;

/**
 * Created by erick on 22/06/16.
 */
public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);
}
