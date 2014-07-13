package com.meghan.messagemax;

import java.util.LinkedList;
import java.util.List;
import com.example.messagemax.R;

/*
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.CursorAdapter;
import android.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.SearchView.OnQueryTextListener;*/

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;



//public class ContactFragment extends Fragment { 
public class ContactFragment extends ListFragment implements LoaderCallbacks<Cursor> {	
    private CursorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create adapter once
        Context context = getActivity();
        int layout = android.R.layout.simple_list_item_1;
        Cursor c = null; // there is no cursor yet
        int flags = 0; // no auto-requery! Loader requeries.
        mAdapter = new SimpleCursorAdapter(context, layout, c, FROM, TO, flags);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // each time we are started use our listadapter
        setListAdapter(mAdapter);
        // and tell loader manager to start loading
        getLoaderManager().initLoader(0, null, this);
    }

    // columns requested from the database
    private static final String[] PROJECTION = {
        Contacts._ID, // _ID is always required
        Contacts.DISPLAY_NAME_PRIMARY // that's what we want to display
    };

    // and name should be displayed in the text1 textview in item layout
    private static final String[] FROM = { Contacts.DISPLAY_NAME_PRIMARY };
    private static final int[] TO = { android.R.id.text1 };

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        // load from the "Contacts table"
        Uri contentUri = Contacts.CONTENT_URI;

        // no sub-selection, no sort order, simply every row
        // projection says we want just the _id and the name column
        return new CursorLoader(getActivity(),
                contentUri,
                PROJECTION,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Once cursor is loaded, give it to adapter
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // on reset take any old cursor away
        mAdapter.swapCursor(null);
    }	
	
/*	//private ContactAdapter mAdapter;
	private List contactItemList = new  LinkedList<ContactItem>();
	private LayoutInflater mInflater;
	private FragmentActivity myContext;
	public boolean taskRun = false;
	long currentID = 0;
	long currentContactID = 0; 
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
			android.support.v4.app.FragmentManager fragmentManager = myContext.getSupportFragmentManager();
			android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


	        // Create the list fragment and add it as our sole content.
	        if (fragmentManager.findFragmentById(R.id.contact_list) == null) {
	        	
	            ListContactFragment list = new ListContactFragment();
	            fragmentManager.beginTransaction().add(R.id.contact_list, list).commit();
	        }

        return inflater.inflate(R.layout.fragment_contact, container, false);     
	}
	
	@Override
	public void onAttach(Activity activity) {
		myContext = (FragmentActivity) activity;
		super.onAttach(activity);
	}*/
}