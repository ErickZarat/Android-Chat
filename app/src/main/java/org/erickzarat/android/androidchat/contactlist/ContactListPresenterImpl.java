package org.erickzarat.android.androidchat.contactlist;

import org.erickzarat.android.androidchat.contactlist.events.ContactListEvent;
import org.erickzarat.android.androidchat.contactlist.ui.ContactListView;
import org.erickzarat.android.androidchat.entities.User;
import org.erickzarat.android.androidchat.lib.EventBus;
import org.erickzarat.android.androidchat.lib.GreenRobotEventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by erick on 22/06/16.
 */
public class ContactListPresenterImpl implements ContactListPresenter{
    private EventBus eventBus;
    private ContactListView view;
    private ContactListInteractor listInteractor;
    private ContactListSessionInteractor sessionInteractor;

    public ContactListPresenterImpl(ContactListView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        this.listInteractor = new ContactListInteractorImpl();
        this.sessionInteractor = new ContactListSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConnectionStatus(User.ONLINE);
        listInteractor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        listInteractor.destroyListener();
        view = null;
    }

    @Override
    public void signOff() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
        listInteractor.destroyListener();
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    public void onContactAdded(User user) {
        if (view != null) {
            view.onContactAdded(user);
        }
    }

    public void onContactChanged(User user) {
        if (view != null) {
            view.onContactChanged(user);
        }
    }

    public void onContactRemoved(User user) {
        if (view != null) {
            view.onContactRemoved(user);
        }
    }
}
