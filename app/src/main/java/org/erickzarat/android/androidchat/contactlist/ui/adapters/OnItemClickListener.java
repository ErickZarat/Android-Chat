package org.erickzarat.android.androidchat.contactlist.ui.adapters;

import org.erickzarat.android.androidchat.entities.User;

/**
 * Created by erick on 22/06/16.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
