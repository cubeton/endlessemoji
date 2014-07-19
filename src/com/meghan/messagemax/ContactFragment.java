package com.meghan.messagemax;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.example.messagemax.R;
import com.meghan.messagemax.RepeatsFragment.onRepeatListener;

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

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



//public class ContactFragment extends Fragment { 
public class ContactFragment extends ListFragment implements LoaderCallbacks<Cursor> {	
    private CursorAdapter mAdapter;
    onNumberListener mCallback;
    ArrayList<String> names;
    ArrayList<String> numbers;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("adding contacts");
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
        System.out.println("adding contacts right now");
        // load from the "Contacts table"
        Uri contentUri = Contacts.CONTENT_URI;

        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        String[] name_values = new String[phones.getCount()];
        names = new ArrayList<String>();
        numbers = new ArrayList<String>();
        while (phones.moveToNext())
         {
           String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
       		names.add(name);
           System.out.println(name);
           String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
           numbers.add(number);
           System.out.println(number);
        }
        phones.close();
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.contact_list_item, R.id.name, names);
        setListAdapter(adapter);

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
   public void onListItemClick(ListView l, View v, int position, long id) {
	   System.out.println(position);
	   System.out.println(getListAdapter().getItem(position));  
	   mCallback.onNumberSaved(numbers.get((int) id));
	   
   	  ((MainActivity)getActivity()).setCurrentItem(3); //Switch to next fragment (ContactFragment is fragment 2)
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
    
    public interface onNumberListener {
    	public void onNumberSaved(String number);
    }
    
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	try {
    		mCallback = (onNumberListener) activity;    		
    	} catch(ClassCastException e) {
    		throw new ClassCastException(activity.toString() + " RAWRmust implement onNumberListener");
    	}
    }
}