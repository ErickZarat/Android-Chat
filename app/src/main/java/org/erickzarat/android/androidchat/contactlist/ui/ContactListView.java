package org.erickzarat.android.androidchat.contactlist.ui;

import org.erickzarat.android.androidchat.entities.User;

/**
 * Created by erick on 22/06/16.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
