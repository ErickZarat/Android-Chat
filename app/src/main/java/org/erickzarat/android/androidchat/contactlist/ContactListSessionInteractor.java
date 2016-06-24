package org.erickzarat.android.androidchat.contactlist;

/**
 * Created by erick on 22/06/16.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
