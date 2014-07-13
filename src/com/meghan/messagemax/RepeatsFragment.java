package com.meghan.messagemax;

import com.example.messagemax.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;
 
public class RepeatsFragment extends Fragment {
	onRepeatListener mCallback;
	View rootView;
	NumberPicker np;
	Button repeat_button;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.fragment_repeats, container, false);
 
        np = (NumberPicker) rootView.findViewById(R.id.np);     
        np.setMinValue(1);
        np.setMaxValue(100);
        np.setValue(10);
        np.setWrapSelectorWheel(true);
        
        repeat_button = (Button) rootView.findViewById(R.id.repeat_button);
        repeat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { 	
            	mCallback.onRepeatSaved(np.getValue());
            	((MainActivity)getActivity()).setCurrentItem(2); //Switch to next fragment (RepeatFragment is fragment 1)
            }
        });   
        
        return rootView;
    }
    
    public interface onRepeatListener {
    	public void onRepeatSaved(int repeat);
    }
    
    @Override
    public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	
    	try {
    		mCallback = (onRepeatListener) activity;
    	}
    	catch(ClassCastException e) {
    		throw new ClassCastException(activity.toString() + " must implement onRepeatListener");
    	}
    }
}