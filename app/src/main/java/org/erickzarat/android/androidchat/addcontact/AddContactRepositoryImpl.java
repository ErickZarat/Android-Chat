package org.erickzarat.android.androidchat.addcontact;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import org.erickzarat.android.androidchat.addcontact.events.AddContactEvent;
import org.erickzarat.android.androidchat.domain.FirebaseHelper;
import org.erickzarat.android.androidchat.entities.User;
import org.erickzarat.android.androidchat.lib.EventBus;
import org.erickzarat.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by zarathos on 24/06/16
 */
public class AddContactRepositoryImpl implements AddContactRepository {
    EventBus eventBus;
    FirebaseHelper helper;

    public AddContactRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void addContact(final String email) {
        final String key = email.replace(".", "_");
        Firebase userReference =helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null){
                    Firebase myContactReference = helper.getMyContactsReference();
                    myContactReference.child(key).setValue(user.isOnline());

                    String currentUserKey = helper.getAuthUserEmail();
                    currentUserKey = currentUserKey.replace(".", "_");

                    Firebase reverseContactReference = helper.getContactsReference(email);
                    reverseContactReference.child(currentUserKey).setValue(User.ONLINE);

                    postSuccess();
                }else {
                    postError();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                postError();
            }
        });
    }

    private void postSuccess() {
        post(false);
    }

    private void postError(){
        post(true);
    }

    private void post(boolean error) {
        AddContactEvent event= new AddContactEvent();
        event.setError(error);
        eventBus.post(event);
    }
}
