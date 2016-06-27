package org.erickzarat.android.androidchat.contactlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import org.erickzarat.android.androidchat.R;
import org.erickzarat.android.androidchat.addcontact.ui.AddContactFragment;
import org.erickzarat.android.androidchat.chat.ui.ChatActivity;
import org.erickzarat.android.androidchat.contactlist.ContactListPresenter;
import org.erickzarat.android.androidchat.contactlist.ContactListPresenterImpl;
import org.erickzarat.android.androidchat.contactlist.ui.adapters.ContactListAdapter;
import org.erickzarat.android.androidchat.contactlist.ui.adapters.OnItemClickListener;
import org.erickzarat.android.androidchat.domain.AvatarHelper;
import org.erickzarat.android.androidchat.entities.User;
import org.erickzarat.android.androidchat.lib.GlideImageLoader;
import org.erickzarat.android.androidchat.lib.ImageLoader;
import org.erickzarat.android.androidchat.login.ui.LoginActivity;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener {

    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    private ContactListPresenter presenter;
    private ContactListAdapter adapter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();
        presenter = new ContactListPresenterImpl(this);
        presenter.onCreate();
        setupToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            presenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplicationContext());
        adapter = new ContactListAdapter(new ArrayList<User>(), loader, this);
    }

    private void setupToolbar() {
        toolbar.setTitle("");
        String userEmail = presenter.getCurrentUserEmail();
        ImageLoader imageLoader = new GlideImageLoader(getApplicationContext());
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(userEmail));
        txtUser.setText(userEmail);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.fab)
    public void addContact() {
        new AddContactFragment().show(getSupportFragmentManager(), getString(R.string.addcontact_message_title));
    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY, user.isOnline());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(User user) {
        presenter.removeContact(user.getEmail());
    }
}
