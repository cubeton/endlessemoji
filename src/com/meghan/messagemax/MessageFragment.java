package com.meghan.messagemax;

import com.example.messagemax.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
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
           
         
         edit_button.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        	 @Override
        	 public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        		 if (actionId == EditorInfo.IME_ACTION_DONE) {
        			 mCallback.onMessageSaved(edit_button.getText().toString());
        		        edit_button.setInputType(0);
        		        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        		        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        			 ((MainActivity)getActivity()).setCurrentItem(1); //Switch to next fragment (MessageFragment is fragment 0)
        			 return true;
        	 }
        		 return false;
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
