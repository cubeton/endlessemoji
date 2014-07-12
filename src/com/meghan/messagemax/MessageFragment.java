package com.meghan.messagemax;

import com.example.messagemax.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
 
public class MessageFragment extends Fragment {
	onMessageListener mCallback;
	
	EditText edit_button;
	Button message_button;
	ViewPager viewPager; 

	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        edit_button = (EditText) rootView.findViewById(R.id.edit_text);

        
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
 
         message_button = (Button) rootView.findViewById(R.id.message_button);      
         message_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { 
            	mCallback.onMessageSaved(edit_button.getText().toString());
            }
        });                      

        return rootView;
    }
    
    public interface onMessageListener {
    	public void onMessageSaved(String message);
    }
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	
    	try{
    		mCallback = (onMessageListener) activity;
    	}
    	catch(ClassCastException e) {
    		throw new ClassCastException(activity.toString() + " must implement onMessageListener");
    	}
    }
}
