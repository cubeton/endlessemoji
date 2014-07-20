package com.meghan.endlessemoji;

import java.util.ArrayList;

import com.example.messagemax.R;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class ContactAdapter extends ArrayAdapter<String>{
    public ContactAdapter(Context context, ArrayList<String> names) {
        super(context, R.layout.contact_list_item, names);
    } 

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = (TextView) super.getView(position, convertView, parent);
        if(position % 2 == 0)
        {
            view.setBackgroundColor(Color.parseColor("#b3e5fc"));
        } else {
        	view.setBackgroundColor(Color.parseColor("#b2ebf2"));
        }
        return view;
    }
 
}