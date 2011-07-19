package id.co.mondial.android.rss.mtsreader;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class RssFeeds extends ListActivity {
    
	public static int channelId = 0;
	GoogleAnalyticsTracker tracker;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    	tracker = GoogleAnalyticsTracker.getInstance();
    	tracker.start(getResources().getString(R.string.analytics_tracker_id), this);
        
    	setContentView(R.layout.main_list);
        
        String[] channels = getResources().getStringArray(R.array.channels_title);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.feeds, channels));

        tracker.trackPageView("/" + getResources().getString(R.string.tracker_home));
    	tracker.dispatch();
        
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	super.onListItemClick(l, v, position, id);
    	
    	// clear serialized data on detail activity
    	SharedPreferences itemsSerialized = getSharedPreferences(RssItems.SERIALIZED_NAME, 0);
    	SharedPreferences.Editor itemsSerializedEditor = itemsSerialized.edit();
    	itemsSerializedEditor.clear();
    	itemsSerializedEditor.commit();
    	
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
    	
    	// clear cache
    	WebView wv = new WebView(this);
    	wv.clearCache(true);
	    
    	// stopping analytics
    	tracker.stop();
    }
    
}