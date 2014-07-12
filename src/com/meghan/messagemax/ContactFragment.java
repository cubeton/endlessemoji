package com.meghan.messagemax;

import java.util.LinkedList;
import java.util.List;
import com.example.messagemax.R;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;


public class ContactFragment extends Fragment { 
	//private ContactAdapter mAdapter;
	private List contactItemList = new  LinkedList<ContactItem>();
	private LayoutInflater mInflater;
	public boolean taskRun = false;
	long currentID = 0;
	long currentContactID = 0; 
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact, container, false);     
	}
/*	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);	
		if(!taskRun){
			FragmentTransaction ft =  getFragmentManager().beginTransaction();
			ListContactTask task= new ListContactTask(getActivity(),ft);
			task.execute();	
		}
		taskRun = true;
		mAdapter = new ContactAdapter(getActivity(), R.layout.list_item, R.id.key, contactItemList);
		mAdapter .setInflater(mInflater);
		mAdapter.setLayout(R.layout.list_item);
		setListAdapter(mAdapter);
		ListView listView = getListView();
		getListView().invalidate();
	}*/


}

/*class ContactAdapter extends ArrayAdapter<ContactItem> {

	   private static String TAG = ContactAdapter.class.getName();
	   private LayoutInflater inflator = null;
	   List<ContactItem> pairList = null;
	   private int layout;
	   
	   public ContactAdapter(Context context, int resource, int textViewResourceId, List<ContactItem> objects) {
			super(context, resource, textViewResourceId, objects);
			this.pairList = objects;
		}

		public void setInflater(LayoutInflater mInflater) {
			this.inflator = mInflater;
		}
		
		public void setLayout(int layout){
			this.layout = layout;
		}

		*//**
		 * Make a view to hold each row.
		 * 
		 * @see android.widget.ListAdapter#getView(int, android.view.View,
		 *      android.view.ViewGroup)
		 *//*
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;
			try {
				if (convertView == null) {
					convertView = this.inflator.inflate(
							layout, null);
					holder = new ViewHolder();
					holder.key = (TextView) convertView
							.findViewById(R.id.key);
					holder.value = (TextView) convertView.findViewById(R.id.value);
					convertView.setTag(holder);
				} else {
					 holder = (ViewHolder) convertView.getTag();
				}
				ContactItem pair = (ContactItem) getItem(position);
				String key = pair.mDisplayName;
				String value = pair.mPhone;

				holder.key.setText(key);
				holder.value.setText(value);
				
			} catch (Exception e) {
				Log.e(TAG, e.toString(), e);
			}
			return convertView;
		}

		static class ViewHolder {
			TextView key;
			TextView value;
		}

		public Filter getFilter() {
			return null;
		}

		public long getItemId(int position) {
			return 1;
		}

		public int getCount() {
			return pairList.size();
		}

		public ContactItem getItem(int position) {
			return (ContactItem) super.getItem(position);
		}

		@Override
		public int getItemViewType(int position) {
			return super.getItemViewType(position);
		}

		@Override
		public int getViewTypeCount() {
			return super.getViewTypeCount();
		}

		@Override
		public boolean isEmpty() {
			return super.isEmpty();
		}
	}

*/