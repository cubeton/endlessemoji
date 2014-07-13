package com.meghan.messagemax;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.SearchView.OnQueryTextListener;

public class ListContactFragment extends ListFragment
implements LoaderManager.LoaderCallbacks<Cursor> {

// This is the Adapter being used to display the list's data.
SimpleCursorAdapter mAdapter;

// If non-null, this is the current filter the user has provided.
String mCurFilter;

@Override public void onActivityCreated(Bundle savedInstanceState) {
super.onActivityCreated(savedInstanceState);

// Give some text to display if there is no data.  In a real
// application this would come from a resource.
setEmptyText("No phone numbers");

// Create an empty adapter we will use to display the loaded data.
mAdapter = new SimpleCursorAdapter(getActivity(),
        android.R.layout.simple_list_item_2, null,
        new String[] { Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER },
        new int[] { android.R.id.text1, android.R.id.text2 }, 0);
setListAdapter(mAdapter);

// Prepare the loader.  Either re-connect with an existing one,
// or start a new one.
getLoaderManager().initLoader(0, null, this);
}

// These are the Contacts rows that we will retrieve.
static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
Contacts._ID,
Contacts.DISPLAY_NAME,
Contacts.CONTACT_STATUS,
Contacts.CONTACT_PRESENCE,
Contacts.PHOTO_ID,
Contacts.LOOKUP_KEY,
};

public Loader<Cursor> onCreateLoader(int id, Bundle args) {
// This is called when a new Loader needs to be created.  This
// sample only has one Loader, so we don't care about the ID.
// First, pick the base URI to use depending on whether we are
// currently filtering.
Uri baseUri;
if (mCurFilter != null) {
    baseUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI,
            Uri.encode(mCurFilter));
} else {
    baseUri = Contacts.CONTENT_URI;
}

// Now create and return a CursorLoader that will take care of
// creating a Cursor for the data being displayed.
String select = "((" + Contacts.DISPLAY_NAME + " NOTNULL) AND ("
        + Contacts.HAS_PHONE_NUMBER + "=1) AND ("
        + Contacts.DISPLAY_NAME + " != '' ))";

return new CursorLoader(getActivity(), baseUri,
        CONTACTS_SUMMARY_PROJECTION, select, null,
        Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
}

public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
// Swap the new cursor in.  (The framework will take care of closing the
// old cursor once we return.)
mAdapter.swapCursor(data);
}

public void onLoaderReset(Loader<Cursor> loader) {
// This is called when the last Cursor provided to onLoadFinished()
// above is about to be closed.  We need to make sure we are no
// longer using it.
mAdapter.swapCursor(null);
}

}
