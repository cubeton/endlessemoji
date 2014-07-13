package com.meghan.messagemax;

import com.example.messagemax.R;
import com.meghan.messagemax.MessageFragment.onMessageListener;
import com.meghan.tabsswipe.adapter.TabsPagerAdapter;

import android.app.Activity;
import android.view.MenuItem;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.telephony.SmsManager;
import android.view.Menu;
import android.widget.NumberPicker;
import android.widget.Toast;
 

public class MainActivity extends FragmentActivity implements ActionBar.TabListener, 
																	MessageFragment.onMessageListener,
																	RepeatsFragment.onRepeatListener,
																	ContactFragment.onNumberListener,
																	SendFragment.onSendListener {
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	String message;
	int repeat = -1;
	String number;
	
	//Tab titles
	private String[] tabs = {"Message", "Repeats", "Contacts", "Send!"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		viewPager.setAdapter(mAdapter);
        
		//Add tabs to screen
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
		}		        

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                actionBar.setSelectedNavigationItem(position);
                invalidateOptionsMenu();
            }
        });
    }

	public void setCurrentItem(int item) {
		viewPager.setCurrentItem(item, true);
	}
	
	public void onMessageSaved(String message) {
    	Toast.makeText(getApplicationContext(), "Message saved!", Toast.LENGTH_SHORT).show();     		
    	this.message = message;
	}
	
	public void onRepeatSaved(int repeat) {
		Toast.makeText(getApplicationContext(), "Repeats saved!", Toast.LENGTH_SHORT).show();     
    	this.repeat = repeat;
	}
	
	public void onNumberSaved(String number) {
		Toast.makeText(getApplicationContext(), "Number saved!", Toast.LENGTH_SHORT).show();     
    	this.number = number;
	}

	public void onSendSaved() {
		Toast.makeText(getApplicationContext(), "This is a test of my new app. Do not be alarmed!!", Toast.LENGTH_SHORT).show();     
		try {
			SmsManager.getDefault().sendTextMessage("+15712058980", null, "This is a test of my new app. Do not be alarmed!!", null, null);
			} catch (Exception e) {
			AlertDialog.Builder alertDialogBuilder = new

			AlertDialog.Builder(this);

			AlertDialog dialog = alertDialogBuilder.create();

			dialog.setMessage(e.getMessage());
			dialog.show();
		}
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

        menu.findItem(R.id.action_previous).setEnabled(viewPager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (viewPager.getCurrentItem() == mAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                invalidateOptionsMenu();
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                invalidateOptionsMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
 
}