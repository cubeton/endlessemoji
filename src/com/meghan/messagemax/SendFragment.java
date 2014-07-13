package com.meghan.messagemax;

import com.example.messagemax.R;
import com.meghan.messagemax.RepeatsFragment.onRepeatListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
 
public class SendFragment extends Fragment { 
	onSendListener mCallback;
	View rootView;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	System.out.println("adding button");
        
        rootView = inflater.inflate(R.layout.fragment_send, container, false);
        Button send_button = (Button) rootView.findViewById(R.id.send_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { 	
            	mCallback.onSendSaved();
            }
        });   
        System.out.println("adding button");
        
        return rootView;
    }
    
    public interface onSendListener {
    	public void onSendSaved();
    }
    
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	try {
    		mCallback = (onSendListener) activity;
    	}
    	catch(ClassCastException e) {
    		throw new ClassCastException(activity.toString() + " must implement onSendListener");
    	}
    }
}