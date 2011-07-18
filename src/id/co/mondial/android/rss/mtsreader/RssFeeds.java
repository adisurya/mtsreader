package id.co.mondial.android.rss.mtsreader;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RssFeeds extends ListActivity {
    
	public static int channelId = 0;
	GoogleAnalyticsTracker tracker;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    	tracker = GoogleAnalyticsTracker.getInstance();
    	tracker.start("UA-22304301-3", this);
        
    	setContentView(R.layout.main_list);
        
        String[] channels = getResources().getStringArray(R.array.channels_title);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, channels));

        tracker.trackPageView("/" + getResources().getString(R.string.tracker_prefix) + "/home");
    	tracker.dispatch();
        
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	super.onListItemClick(l, v, position, id);
    	
    	channelId = position;
    	Intent channelContentsIntent = new Intent(this, RssItems.class);
    	startActivity(channelContentsIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
        	case R.id.about:
            	Intent aboutIntent = new Intent(this, About.class);
            	startActivity(aboutIntent);
            	return true;
	        default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	tracker.stop();
    }

}