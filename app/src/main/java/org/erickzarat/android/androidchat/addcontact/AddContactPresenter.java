package org.erickzarat.android.androidchat.addcontact;

import org.erickzarat.android.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by zarathos on 24/06/16
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
