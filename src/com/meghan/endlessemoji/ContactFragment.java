package com.meghan.endlessemoji;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.example.messagemax.R;
import com.meghan.endlessemoji.RepeatsFragment.onRepeatListener;

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
public class ContactFragment extends ListFragment{	
    private CursorAdapter mAdapter;
    onNumberListener mCallback;
    ArrayList<String> names;
    ArrayList<String> numbers;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // create adapter once
        Context context = getActivity();
        int layout = android.R.layout.simple_list_item_1;
        Cursor c = null; // there is no cursor yet
        int flags = 0; // no auto-requery! Loader requeries.
        mAdapter = new SimpleCursorAdapter(context, layout, c, FROM, TO, flags);
        // each time we are started use our listadapter
        setListAdapter(mAdapter);
      
        // load from the "Contacts table"
        Uri contentUri = Contacts.CONTENT_URI;

       Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
       String[] name_values = new String[phones.getCount()];
       names = new ArrayList<String>();
       numbers = new ArrayList<String>();
        
       while (phones.moveToNext())
       	{
       	   names.add(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
           numbers.add(phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
        }
        phones.close();
        
       
       setListAdapter(new ContactAdapter(getActivity(), names));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    
    private static final String[] FROM = { Contacts.DISPLAY_NAME_PRIMARY };
    private static final int[] TO = { android.R.id.text1 };

   @Override
   public void onListItemClick(ListView l, View v, int position, long id) {
	   mCallback.onNumberSaved(numbers.get((int) id));	   
   	  ((MainActivity)getActivity()).setCurrentItem(3); //Switch to next fragment (ContactFragment is fragment 2)
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
    		throw new ClassCastException(activity.toString() + " must implement onNumberListener");
    	}
    }

}